package syntaxAnalyzer;

public class ExponentIndicator{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " e " : return true;
		case " E" : return true;
		default : return false;
	}
}
}
