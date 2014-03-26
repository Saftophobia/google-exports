package syntaxAnalyzer;

public class SwitchLabel{

public static Object eval(Object o ){

	if( 	"case".equals((String)o) &&
 	((Boolean)ConstantExpression.eval(o)) &&
 	":".equals((String)o) ){
		return true;
	}


	if( 	"default".equals((String)o) &&
 	":".equals((String)o) ){
		return true;
	}

	return false;
}
}
