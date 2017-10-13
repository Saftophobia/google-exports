package syntaxAnalyzer;

public class FormalParameterList{

public static Object eval(Object o ){

	if( 	((Boolean)FormalParameter.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FormalParameterList.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)FormalParameter.eval(o)) ){
		return true;
	}

	return false;
}
}
