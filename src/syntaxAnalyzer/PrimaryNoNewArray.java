package syntaxAnalyzer;

public class PrimaryNoNewArray{

public static Object eval(Object o ){

	if( 	((Boolean)Literal.eval(o)) ){
		return true;
	}


	if( 	"this".equals((String)o) ){
		return true;
	}


	if( 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)ClassInstanceCreationExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FieldAccess.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodInvocation.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArrayAccess.eval(o)) ){
		return true;
	}

	return false;
}
}
