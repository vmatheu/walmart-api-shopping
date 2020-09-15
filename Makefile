assemble:
	./gradlew clean updateDependencies assemble

clean:
	./gradlew clean

run: assemble
	java -jar build/libs/*.jar

unit-test:
	./gradlew unitTest

