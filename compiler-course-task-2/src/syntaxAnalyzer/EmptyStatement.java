package syntaxAnalyzer;

public class EmptyStatement{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " ;" : return true;
		default : return false;
	}
}
}
