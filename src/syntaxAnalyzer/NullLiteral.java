package syntaxAnalyzer;

public class NullLiteral{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " null" : return true;
		default : return false;
	}
}
}
