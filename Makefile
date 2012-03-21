.PHONY : launch push package clean db-reset schema-%

APP_NAME=demo
BUILD_DIR=build
SCHEMA_DIR=schema

launch: push
	open `dotcloud url $(APP_NAME) | sed s/www://`

push: package
	dotcloud push --all $(APP_NAME)

package:
	mkdir -p $(BUILD_DIR)
	cp -R php/* $(BUILD_DIR)
	cp -R static/* $(BUILD_DIR)

clean:
	rm -Rf $(BUILD_DIR)

db-reset: schema-drop schema-create

schema-%:
	dotcloud run $(APP_NAME).db -- mysql < $(SCHEMA_DIR)/$(APP_NAME)-$*.sql

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)
