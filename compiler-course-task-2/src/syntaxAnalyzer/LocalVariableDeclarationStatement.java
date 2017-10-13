package syntaxAnalyzer;

public class LocalVariableDeclarationStatement{

public static Object eval(Object o ){

	if( 	((Boolean)LocalVariableDeclaration.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
