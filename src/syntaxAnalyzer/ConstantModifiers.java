package syntaxAnalyzer;

public class ConstantModifiers{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " static " : return true;
		case " final" : return true;
		default : return false;
	}
}
}
