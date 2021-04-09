SRC = src/*.java src/vsop/*.java

COMPILER = vsopc
TARGET = target
GRAMMARS = grammars
LEXER = VSOPLexer.g4
PARSER = VSOPParser.g4
TOKENS = VSOPLexer.tokens
BIN = bin
ANTLR = antlr-4.9.1-complete.jar
MAIN_CLASS = Compiler

.PHONY: all install-tools clean

all: vsopc

vsopc:
	java -jar $(ANTLR) -o $(TARGET) $(GRAMMARS)/$(LEXER) 
	cp $(TARGET)/$(GRAMMARS)/$(TOKENS) $(GRAMMARS)/$(TOKENS)
	java -jar $(ANTLR) -o $(TARGET) $(GRAMMARS)/$(PARSER) -visitor 
	javac -cp $(ANTLR) -d $(BIN) $(SRC) $(TARGET)/$(GRAMMARS)/*.java
	cat vsopc.sh > vsopc
	chmod u+x $(COMPILER)

install-tools:

clean:
	rm -f -r $(BIN) $(TARGET)
