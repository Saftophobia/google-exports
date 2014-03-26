package syntaxAnalyzer;

public class SimpleTypeName{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) ){
		return true;
	}

	return false;

}
}
