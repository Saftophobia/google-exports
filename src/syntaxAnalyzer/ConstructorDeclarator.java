package syntaxAnalyzer;

public class ConstructorDeclarator{

public static Object eval(Object o ){

	if( 	((Boolean)SimpleTypeName.eval(o)) &&
 	"(".equals((String)o) &&
 	")".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)SimpleTypeName.eval(o)) &&
 	"(".equals((String)o) &&
 	((Boolean)FormalParameterList.eval(o)) &&
 	")".equals((String)o) ){
		return true;
	}

	return false;

}
}
