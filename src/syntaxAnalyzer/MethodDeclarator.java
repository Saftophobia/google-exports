package syntaxAnalyzer;

public class MethodDeclarator{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)Identifier.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)FormalParameterList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}

	return false;

}
}
