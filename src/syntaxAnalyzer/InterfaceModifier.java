package syntaxAnalyzer;

public class InterfaceModifier{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " abstract" : return true;
		default : return false;
	}
}
}
