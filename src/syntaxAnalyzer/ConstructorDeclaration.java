package syntaxAnalyzer;

public class ConstructorDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ConstructorDeclarator.eval(o)) &&
 	((Boolean)ConstructorBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConstructorModifiers.eval(o)) &&
 	((Boolean)ConstructorDeclarator.eval(o)) &&
 	((Boolean)ConstructorBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConstructorDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) &&
 	((Boolean)ConstructorBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConstructorModifiers.eval(o)) &&
 	((Boolean)ConstructorDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) &&
 	((Boolean)ConstructorBody.eval(o)) ){
		return true;
	}

	return false;

}
}
