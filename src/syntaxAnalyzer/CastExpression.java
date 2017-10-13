package syntaxAnalyzer;

public class CastExpression{

public static Object eval(Object o ){

	if( 	"(".equals((String)o) &&
 	((Boolean)PrimitiveType.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	"(".equals((String)o) &&
 	((Boolean)ReferenceType.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)UnaryExpressionNotPlusMinus.eval(o)) ){
		return true;
	}

	return false;
}
}
