all: build

build: buildDir
	javac -d build src/Client.java src/Main.java src/Board.java src/Mark.java src/Move.java src/Position.java src/Bot.java src/BotNew.java

launchLocal: build
	java -cp build Main --local

launchClient: build
	java -cp build Main --client

buildDir:
	mkdir -p build

clean:
	rm -r build
