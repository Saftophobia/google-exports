package syntaxAnalyzer;

public class ExponentPart{

public static Object eval(Object o ){

	if( 	((Boolean)ExponentIndicator.eval(o)) &&
 	((Boolean)SignedInteger.eval(o)) ){
		return true;
	}

	return false;

}
}
