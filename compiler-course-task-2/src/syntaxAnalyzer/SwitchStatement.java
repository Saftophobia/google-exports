package syntaxAnalyzer;

public class SwitchStatement{

public static Object eval(Object o ){

	if( 	"switch".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)SwitchBlock.eval(o)) ){
		return true;
	}

	return false;

}
}
