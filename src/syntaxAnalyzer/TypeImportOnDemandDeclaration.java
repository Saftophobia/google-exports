package syntaxAnalyzer;

public class TypeImportOnDemandDeclaration{

public Object eval(Object o ){

	if( 	"import".equals((String)o) &&
 	PackageName.eval(o) &&
 	".".equals((String)o) &&
 	"*".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
