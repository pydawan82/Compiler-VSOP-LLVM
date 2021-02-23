SRC = src/*.java
LEXER = targets/grammars/*.java
DEST = bin
ANTLR = antlr-4.9.1-complete.jar

all:
	java -jar antlr-4.9.1-complete.jar -o targets ./grammars/VSOPLexer.g4
	javac -cp antlr-4.9.1-complete.jar -d $(DEST) $(SRC) $(LEXER)

install-tools:
	wget https://www.antlr.org/download/antlr-4.9.1-complete.jar
