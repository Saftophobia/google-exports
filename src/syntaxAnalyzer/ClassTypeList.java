package syntaxAnalyzer;

public class ClassTypeList{

public static Object eval(Object o ){

	if( 	((Boolean)ClassType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassTypeList.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)ClassType.eval(o)) ){
		return true;
	}

	return false;
}
}
