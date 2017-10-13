package syntaxAnalyzer;

public class TypeImportOnDemandDeclaration{

public static Object eval(Object o ){

	if( 	"import".equals((String)o) &&
 	((Boolean)PackageName.eval(o)) &&
 	".".equals((String)o) &&
 	"*".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
