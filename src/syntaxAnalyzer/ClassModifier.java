package syntaxAnalyzer;

public class ClassModifier{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " abstract " : return true;
		case " final" : return true;
		default : return false;
	}
}
}
