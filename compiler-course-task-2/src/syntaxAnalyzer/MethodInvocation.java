package syntaxAnalyzer;

public class MethodInvocation{

public static Object eval(Object o ){

	if( 	((Boolean)MethodName.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)MethodName.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)Primary.eval(o)) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)Primary.eval(o)) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"super".equals((String)o) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"super".equals((String)o) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}

	return false;
}
}
