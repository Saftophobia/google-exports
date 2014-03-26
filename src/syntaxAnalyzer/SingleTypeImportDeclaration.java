package syntaxAnalyzer;

public class SingleTypeImportDeclaration{

public static Object eval(Object o ){

	if( 	"import".equals((String)o) &&
 	((Boolean)TypeName.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
