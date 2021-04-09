package vsop;

import java.util.List;
import java.util.Map;

public class VSOPConstants {
	public static final VSOPType INT32 = new VSOPType("int32", 0);
	public static final VSOPType STRING = new VSOPType("string", "");
	public static final VSOPType UNIT = new VSOPType("unit", "()");
	public static final VSOPType BOOL = new VSOPType("bool", false);
	public static final Map<String, VSOPType> primitiveTypeMap = 
			Map.of(INT32.id, INT32, STRING.id, STRING, UNIT.id, UNIT, BOOL.id, BOOL);
	public static final List<VSOPType> primitiveTypes = List.of(INT32, STRING, UNIT, BOOL);
	
	public static final VSOPClass OBJECT = new VSOPClass("Object", null, Map.of(), Map.of(), 0, 0);
	
	private static final VSOPMethod print = new VSOPMethod("print", List.of(new VSOPField("s", STRING, 0, 0)), OBJECT, 0, 0);
	private static final VSOPMethod printBool = new VSOPMethod("printBool", List.of(new VSOPField("s", STRING, 0, 0)), OBJECT, 0, 0);
	private static final VSOPMethod printInt32 = new VSOPMethod("printInt32", List.of(new VSOPField("s", STRING, 0, 0)), OBJECT, 0, 0);
	
	private static final VSOPMethod inputLine = new VSOPMethod("inputLine", List.of(new VSOPField("s", STRING, 0, 0)), STRING, 0, 0);
	private static final VSOPMethod inputBool = new VSOPMethod("inputBool", List.of(new VSOPField("s", STRING, 0, 0)), BOOL, 0, 0);
	private static final VSOPMethod inputInt32 = new VSOPMethod("inputInt32", List.of(new VSOPField("s", STRING, 0, 0)), INT32, 0, 0);
	
	static {
		OBJECT.functions.putAll(Map.of(
				print.name, print,
				printBool.name, printBool,
				printInt32.name, printInt32,
				
				inputLine.name, inputLine,
				inputBool.name, inputBool,
				inputInt32.name, inputInt32));
	}
	
	
	public static final List<String> reservedNames = List.of("and", "extends", "isnull", "false", "let", "then", "class", "if",
					"new", "true", "do", "in", "not", "else", "self", "while", "int32", "string", "unit", "bool");
	
	public static final VSOPBinOp AND = new VSOPBinOp("and", BOOL, BOOL);	
	public static final VSOPBinOp EQ = new VSOPBinOp("=", null, BOOL);	
	public static final VSOPBinOp LOW = new VSOPBinOp("<", INT32, BOOL);	
	public static final VSOPBinOp LE = new VSOPBinOp("<=", INT32, BOOL);	
	public static final VSOPBinOp PLUS = new VSOPBinOp("+", INT32, INT32);	
	public static final VSOPBinOp MINUS = new VSOPBinOp("-", INT32, INT32);	
	public static final VSOPBinOp TIMES = new VSOPBinOp("*", INT32, INT32);	
	public static final VSOPBinOp DIV = new VSOPBinOp("/", INT32, INT32);	
	public static final VSOPBinOp POW = new VSOPBinOp("^", INT32, INT32);
	public static final Map<String, VSOPBinOp> binOpMap = Map.of(
			AND.id, AND, 
			EQ.id, EQ, 
			LOW.id, LOW, 
			LE.id, LE, 
			PLUS.id, PLUS, 
			MINUS.id, MINUS, 
			TIMES.id, TIMES, 
			DIV.id, DIV, 
			POW.id, POW);
	
	public static final String MAIN_CLASS = "Main";
	public static final String MAIN_METHOD = "main";
	public static final List<VSOPType> MAIN_ARGS = List.of();
	public static final VSOPType MAIN_RET = INT32;
}
