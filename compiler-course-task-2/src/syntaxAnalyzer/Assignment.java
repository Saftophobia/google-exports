package syntaxAnalyzer;

public class Assignment{

public static Object eval(Object o ){

	if( 	((Boolean)LeftHandSide.eval(o)) &&
 	((Boolean)AssignmentOperator.eval(o)) &&
 	((Boolean)AssignmentExpression.eval(o)) ){
		return true;
	}

	return false;

}
}
