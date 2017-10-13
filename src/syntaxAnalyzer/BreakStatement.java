package syntaxAnalyzer;

public class BreakStatement{

public static Object eval(Object o ){

	if( 	"break".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	"break".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
