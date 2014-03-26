package syntaxAnalyzer;

public class SwitchBlock{

public static Object eval(Object o ){

	if( 	"{".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)SwitchBlockStatementGroups.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)SwitchLabels.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)SwitchBlockStatementGroups.eval(o)) &&
 	((Boolean)SwitchLabels.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}

	return false;

}
}
