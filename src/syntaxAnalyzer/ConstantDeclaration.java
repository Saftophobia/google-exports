package syntaxAnalyzer;

public class ConstantDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ConstantModifiers.eval(o)) &&
 	((Boolean)Type.eval(o)) &&
 	((Boolean)VariableDeclarator.eval(o)) ){
		return true;
	}

	return false;

}
}
