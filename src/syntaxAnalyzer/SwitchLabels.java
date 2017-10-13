package syntaxAnalyzer;

public class SwitchLabels{

public static Object eval(Object o ){

	if( 	((Boolean)SwitchLabel.eval(o)) ){
		return true;
	}


	if( 	((Boolean)SwitchLabels.eval(o)) &&
 	((Boolean)SwitchLabel.eval(o)) ){
		return true;
	}

	return false;
}
}
