package syntaxAnalyzer;

public class ExplicitConstructorInvocation{

public static Object eval(Object o ){

	if( 	"this".equals((String)o) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"this".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"super".equals((String)o) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"super".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}

	return false;
}
}
