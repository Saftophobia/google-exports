package syntaxAnalyzer;

public class IntegralType{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " byte " : return true;
		case " short " : return true;
		case " int " : return true;
		case " long " : return true;
		case " char" : return true;
		default : return false;
	}
}
}
