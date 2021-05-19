SRC = src

COMPILER = vsopc
TARGET = target
PACKAGE = compiler.parsing
PACKAGE_DIR = compiler/parsing
GRAMMARS = grammars
LEXER = VSOPLexer.g4
PARSER = VSOPParser.g4
TOKENS = VSOPLexer.tokens
BIN = bin
ANTLR = antlr-4.9.1-complete.jar
MAIN_CLASS = Compiler

RUNTIME = compiler/llvm/runtime.ll

.PHONY: all install-tools clean

all: vsopc

vsopc:
	java -jar $(ANTLR) -o $(TARGET)/$(PACKAGE_DIR) -package $(PACKAGE) $(GRAMMARS)/$(LEXER) 
	cp $(TARGET)/$(PACKAGE_DIR)/$(GRAMMARS)/$(TOKENS) $(GRAMMARS)/$(TOKENS)

	java -jar $(ANTLR) -o $(TARGET)/$(PACKAGE_DIR) -package $(PACKAGE) $(GRAMMARS)/$(PARSER)

	mv $(TARGET)/$(PACKAGE_DIR)/$(GRAMMARS)/* $(TARGET)/$(PACKAGE_DIR)

	javac -cp $(ANTLR) -d $(BIN) -sourcepath $(TARGET):$(SRC) $(SRC)/compiler/$(MAIN_CLASS).java

	cp $(SRC)/$(RUNTIME) $(BIN)/$(RUNTIME)

	cat vsopc.sh > vsopc
	chmod u+x $(COMPILER)

install-tools:
	
clean:
	rm -f -r $(BIN) $(TARGET)
