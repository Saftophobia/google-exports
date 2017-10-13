package syntaxAnalyzer;

public class InterfaceType{

public static Object eval(Object o ){

	if( 	((Boolean)TypeName.eval(o)) ){
		return true;
	}

	return false;

}
}
