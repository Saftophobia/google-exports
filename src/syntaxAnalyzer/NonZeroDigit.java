package syntaxAnalyzer;

public class NonZeroDigit{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " 1 " : return true;
		case " 2 " : return true;
		case " 3 " : return true;
		case " 4 " : return true;
		case " 5 " : return true;
		case " 6 " : return true;
		case " 7 " : return true;
		case " 8 " : return true;
		case " 9" : return true;
		default : return false;
	}
}
}
