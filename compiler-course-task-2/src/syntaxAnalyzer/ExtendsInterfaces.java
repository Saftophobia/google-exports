package syntaxAnalyzer;

public class ExtendsInterfaces{

public static Object eval(Object o ){

	if( 	"extends".equals((String)o) &&
 	((Boolean)InterfaceType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ExtendsInterfaces.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)InterfaceType.eval(o)) ){
		return true;
	}

	return false;
}
}
