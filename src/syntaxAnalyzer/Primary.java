package syntaxAnalyzer;

public class Primary{

public static Object eval(Object o ){

	if( 	((Boolean)PrimaryNoNewArray.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArrayCreationExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
