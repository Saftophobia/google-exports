package syntaxAnalyzer;

public class LocalVariableDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)Type.eval(o)) &&
 	((Boolean)VariableDeclarators.eval(o)) ){
		return true;
	}

	return false;

}
}
