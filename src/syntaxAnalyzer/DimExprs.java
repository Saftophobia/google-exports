package syntaxAnalyzer;

public class DimExprs{

public static Object eval(Object o ){

	if( 	((Boolean)DimExpr.eval(o)) ){
		return true;
	}


	if( 	((Boolean)DimExprs.eval(o)) &&
 	((Boolean)DimExpr.eval(o)) ){
		return true;
	}

	return false;
}
}
