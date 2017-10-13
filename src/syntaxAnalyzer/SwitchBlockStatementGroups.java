package syntaxAnalyzer;

public class SwitchBlockStatementGroups{

public static Object eval(Object o ){

	if( 	((Boolean)SwitchBlockStatementGroup.eval(o)) ){
		return true;
	}


	if( 	((Boolean)SwitchBlockStatementGroups.eval(o)) &&
 	((Boolean)SwitchBlockStatementGroup.eval(o)) ){
		return true;
	}

	return false;
}
}
