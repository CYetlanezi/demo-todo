<?php
require_once 'config.php';
require_once 'pdotemplate.php';
require_once 'entitymanager.php';
require_once 'todos.php';

/*
 * Provides a mapping of entity URL roots to EntityManager instances.
 */
$entities = array(
    "todos" => "TodoEntityManager",
);

/* quick and dirty routing of requests ...
 *
 * /entities/* => maps to a RESTful entity
 * /* => maps to a controller
 *
 * a more complex URL routing mechanism could be used if we really needed one.
 */
$uri = $_SERVER['REQUEST_URI'];
$PAT_ENTITY = "/\/entities\/(.*)/";
if (preg_match($PAT_ENTITY, $uri, $groups)) {
    $uri_parts = split('/', $groups[1]);
    $entity = $uri_parts[0];
    if (isset($uri_parts[1])) {
        $entity_id = $uri_parts[1];
    }
    else {
        $entity_id = null;
    }

    if ($entity == "") {
        $status = "403 - Missing entity type.";
        header("Status: " . $status);
        echo $status;
    }
    else {
        if (isset($entities[$entity])) {
            // Determine the HTTP request method.
            // PHP doesn't really seem to handle PUT/DELETE really well, so
            // we check for X-HTTP-Request-Override in the headers, which is
            // set by some JS frameworks (like backbone.js) in XHR.
            // Further research indicates this may be a dotcloud configuration
            // issue and not a PHP issue.
            $method = $_SERVER['REQUEST_METHOD'];
            if (isset($_SERVER['HTTP_X_HTTP_METHOD_OVERRIDE'])) {
                $method = $_SERVER['HTTP_X_HTTP_METHOD_OVERRIDE'];
            }

            $controller = new $entities[$entity]();
            $controller->set_pdo(new PDOTemplate(
                $DB_DSN, $DB_USER, $DB_PASSWORD));

            // route the controller based on the HTTP verb
            if ($method == "GET") {
                if ($entity_id == null) {
                    $results = $controller->index();
                    echo json_encode($results);
                }
                else {
                    $result = $controller->get($entity_id);
                    echo json_encode($result);
                }
            }
            else if ($method == "POST") {
                $dict = json_decode($HTTP_RAW_POST_DATA, true);
                $id = $controller->create($dict);
            }
            else if ($method == "PUT") {
                $dict = json_decode($HTTP_RAW_POST_DATA, true);
                $controller->update($entity_id, $dict);
            }
            else if ($method == "DELETE") {
                $controller->delete($entity_id);
            }
            else {
                $status = "405 - Method '" . $method . "' not allowed.";
                header("Status: " . $status);
                header("Allow: GET POST PUT DELETE");
                echo $status;
            }
        }
        else {
            $status = "404 - Unknown entity '" . $entity . "'.";
            header("Status: " . $status);
            echo $status;
        }
    }
}

?>
