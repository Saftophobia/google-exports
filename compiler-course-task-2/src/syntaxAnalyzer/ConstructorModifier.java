package syntaxAnalyzer;

public class ConstructorModifier{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " protected " : return true;
		case " private" : return true;
		default : return false;
	}
}
}
