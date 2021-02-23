#include <string.h>
#include <stdlib.h>

int main(int argc, char** argv) {
	char command[1024]="java -cp antlr-4.9.1-complete.jar:bin Compiler";

	for(int i=1; i<argc; i++) {
		strcat(command, " ");
		strcat(command, argv[i]);
	}

	system(command);
}
