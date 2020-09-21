assemble:
	./gradlew clean updateDependencies assemble

clean:
	./gradlew clean

run: assemble
	java -jar build/libs/*.jar

unit-test:
	./gradlew unitTest

integration-test:
	./gradlew integrationTest

docker-build: assemble
	docker build -t api-shopping-image .

docker-clean:
	docker stop api-shopping && docker rm api-shopping

docker-run: docker-build
	docker run -d -p 8080:8080 --name api-shopping api-shopping-image

docker-unit-test:
	docker run --rm -v ${PWD}:/shopping -w /shopping openjdk:8 ./gradlew unitTest

docker-integration-test:
	docker run --rm -v ${PWD}:/shopping -w /shopping openjdk:8 ./gradlew integrationTest