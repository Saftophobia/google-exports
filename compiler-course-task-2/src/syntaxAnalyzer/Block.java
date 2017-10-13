package syntaxAnalyzer;

public class Block{

public static Object eval(Object o ){

	if( 	"{".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)BlockStatements.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}

	return false;

}
}
