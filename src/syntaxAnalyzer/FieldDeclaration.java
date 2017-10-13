package syntaxAnalyzer;

public class FieldDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)Type.eval(o)) &&
 	((Boolean)VariableDeclarators.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)FieldModifiers.eval(o)) &&
 	((Boolean)Type.eval(o)) &&
 	((Boolean)VariableDeclarators.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
