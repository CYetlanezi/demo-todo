.PHONY : launch-% push-% package-php package-java clean-% db-reset-% schema-create-% schema-drop-%

APP_NAME=demo
BUILD_DIR=build
SCHEMA_DIR=schema

launch-%: push-%
	open `dotcloud url $(APP_NAME)$* | sed s/www://`

push-%: package-%
	dotcloud push --all $(APP_NAME)$* $(BUILD_DIR)

package-java:
	mkdir -p $(BUILD_DIR)
	sh -c "cd java; mvn package"
	cp java/dotcloud.yml $(BUILD_DIR)

package-php:
	mkdir -p $(BUILD_DIR)
	cp -R php/* $(BUILD_DIR)
	cp -R static/* $(BUILD_DIR)

clean-%: $*
	rm -Rf $(BUILD_DIR)
	yes | dotcloud destroy $(APP_NAME)$*

db-reset: schema-drop schema-create

schema-create:
	-dotcloud run $(APP_NAME)java.db -- mysql < $(SCHEMA_DIR)/$(APP_NAME)-create.sql
	-dotcloud run $(APP_NAME)php.db -- mysql < $(SCHEMA_DIR)/$(APP_NAME)-create.sql

schema-drop:
	-dotcloud run $(APP_NAME)java.db -- mysql < $(SCHEMA_DIR)/$(APP_NAME)-drop.sql
	-dotcloud run $(APP_NAME)php.db -- mysql < $(SCHEMA_DIR)/$(APP_NAME)-drop.sql

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)
