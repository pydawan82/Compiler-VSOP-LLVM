SRC = src/*.java

COMPILER = vsopc
TARGET = target
GRAMMARS = grammars
LEXER = VSOPLexer.g4
BIN = bin
ANTLR = antlr-4.9.1-complete.jar
MAIN_CLASS = Compiler

.PHONY: all install-tools clean

all: vsopc

$(COMPILER):
	java -jar $(ANTLR) -o $(TARGET) $(GRAMMARS)/$(LEXER)
	javac -cp $(ANTLR) -d $(BIN) $(SRC) $(TARGET)/$(GRAMMARS)/*.java
	cat $(COMPILER).sh > $(COMPILER)
	chmod u+x $(COMPILER)

install-tools:

clean:
	rm -f $(COMPILER)
	rm -f -r $(BIN) $(TARGET)


