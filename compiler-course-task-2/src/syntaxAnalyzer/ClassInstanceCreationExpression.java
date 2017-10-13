package syntaxAnalyzer;

public class ClassInstanceCreationExpression{

public static Object eval(Object o ){

	if( 	"new".equals((String)o) &&
 	((Boolean)ClassType.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	"new".equals((String)o) &&
 	((Boolean)ClassType.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)ArgumentList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}

	return false;

}
}
