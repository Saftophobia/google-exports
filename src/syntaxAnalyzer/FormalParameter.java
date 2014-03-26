package syntaxAnalyzer;

public class FormalParameter{

public static Object eval(Object o ){

	if( 	((Boolean)Type.eval(o)) &&
 	((Boolean)VariableDeclaratorId.eval(o)) ){
		return true;
	}

	return false;

}
}
