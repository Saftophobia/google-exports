package syntaxAnalyzer;

public class EmptyStatement{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " ;" : return true;
		default : return false;
	}
}
}
