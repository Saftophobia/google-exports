package syntaxAnalyzer;

public class LabeledStatementNoShortIf{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) &&
 	":".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}

	return false;

}
}
