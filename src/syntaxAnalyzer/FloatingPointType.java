package syntaxAnalyzer;

public class FloatingPointType{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " float " : return true;
		case " double" : return true;
		default : return false;
	}
}
}
