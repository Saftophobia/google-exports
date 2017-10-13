package syntaxAnalyzer;

public class Expression{

public static Object eval(Object o ){

	if( 	((Boolean)AssignmentExpression.eval(o)) ){
		return true;
	}

	return false;

}
}
