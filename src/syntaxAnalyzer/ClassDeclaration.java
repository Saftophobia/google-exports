package syntaxAnalyzer;

public class ClassDeclaration{

public static Object eval(Object o ){

	if( 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassModifiers.eval(o)) &&
 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Super.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Interfaces.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassModifiers.eval(o)) &&
 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Super.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassModifiers.eval(o)) &&
 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Interfaces.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Super.eval(o)) &&
 	((Boolean)Interfaces.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassModifiers.eval(o)) &&
 	"class".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	((Boolean)Super.eval(o)) &&
 	((Boolean)Interfaces.eval(o)) &&
 	((Boolean)ClassBody.eval(o)) ){
		return true;
	}

	return false;

}
}
