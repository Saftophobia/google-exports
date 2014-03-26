package syntaxAnalyzer;

public class VariableInitializers{

public static Object eval(Object o ){

	if( 	((Boolean)VariableInitializer.eval(o)) ){
		return true;
	}


	if( 	((Boolean)VariableInitializers.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)VariableInitializer.eval(o)) ){
		return true;
	}

	return false;
}
}
