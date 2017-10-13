package syntaxAnalyzer;

public class InterfaceTypeList{

public static Object eval(Object o ){

	if( 	((Boolean)InterfaceType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceTypeList.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)InterfaceType.eval(o)) ){
		return true;
	}

	return false;
}
}
