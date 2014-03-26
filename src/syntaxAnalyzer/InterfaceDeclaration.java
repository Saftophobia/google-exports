package syntaxAnalyzer;

public class InterfaceDeclaration{

public static Object eval(Object o ){

	if( 	"interface".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)InterfaceBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceModifiers.eval(o)) &&
 	"interface".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)InterfaceBody.eval(o)) ){
		return true;
	}


	if( 	"interface".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)ExtendsInterfaces.eval(o)) &&
 	((Boolean)InterfaceBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceModifiers.eval(o)) &&
 	"interface".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)ExtendsInterfaces.eval(o)) &&
 	((Boolean)InterfaceBody.eval(o)) ){
		return true;
	}

	return false;

}
}
