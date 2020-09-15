assemble:
	./gradlew clean updateDependencies assemble

clean:
	./gradlew clean

run: assemble
	java -jar build/libs/*.jar

unit-test:
	./gradlew unitTest

docker-build: assemble
	docker build -t api-shopping-image .

docker-clean:
	docker stop api-shopping && docker rm api-shopping

docker-run: docker-build
	docker run --name api-shopping -p 8080:8080 -p 5005:5005 -d shopping-api-image
