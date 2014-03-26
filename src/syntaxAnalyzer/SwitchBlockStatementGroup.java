package syntaxAnalyzer;

public class SwitchBlockStatementGroup{

public static Object eval(Object o ){

	if( 	((Boolean)SwitchLabels.eval(o)) &&
 	((Boolean)BlockStatements.eval(o)) ){
		return true;
	}

	return false;

}
}
