package syntaxAnalyzer;

public class Sign{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " + " : return true;
		case " -" : return true;
		default : return false;
	}
}
}
