package syntaxAnalyzer;

public class PackageDeclaration{

public static Object eval(Object o ){

	if( 	"package".equals((String)o) &&
 	((Boolean)PackageName.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
