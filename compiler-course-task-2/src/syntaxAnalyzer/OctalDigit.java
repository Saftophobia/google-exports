package syntaxAnalyzer;

public class OctalDigit{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " 0 " : return true;
		case " 1 " : return true;
		case " 2 " : return true;
		case " 3 " : return true;
		case " 4 " : return true;
		case " 5 " : return true;
		case " 6 " : return true;
		case " 7" : return true;
		default : return false;
	}
}
}
