package syntaxAnalyzer;

public class MethodModifier{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " protected " : return true;
		case " private " : return true;
		case " static " : return true;
		case " abstract " : return true;
		case " final " : return true;
		case " synchronized " : return true;
		case " native" : return true;
		default : return false;
	}
}
}
