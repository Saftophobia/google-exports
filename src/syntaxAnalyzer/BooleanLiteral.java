package syntaxAnalyzer;

public class BooleanLiteral{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " true " : return true;
		case " false" : return true;
		default : return false;
	}
}
}
