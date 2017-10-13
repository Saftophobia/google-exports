package syntaxAnalyzer;

public class TryStatement{

public static Object eval(Object o ){

	if( 	"try".equals((String)o) &&
 	((Boolean)Block.eval(o)) &&
 	((Boolean)Catches.eval(o)) ){
		return true;
	}


	if( 	"try".equals((String)o) &&
 	((Boolean)Block.eval(o)) &&
 	((Boolean)Finally.eval(o)) ){
		return true;
	}


	if( 	"try".equals((String)o) &&
 	((Boolean)Block.eval(o)) &&
 	((Boolean)Catches.eval(o)) &&
 	((Boolean)Finally.eval(o)) ){
		return true;
	}

	return false;
}
}
