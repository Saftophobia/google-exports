package syntaxAnalyzer;

public class LabeledStatement{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) &&
 	":".equals((String)o) &&
 	((Boolean)Statement.eval(o)) ){
		return true;
	}

	return false;

}
}
