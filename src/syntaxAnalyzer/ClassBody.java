package syntaxAnalyzer;

public class ClassBody{

public static Object eval(Object o ){

	if( 	"{".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)ClassBodyDeclarations.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}

	return false;

}
}
