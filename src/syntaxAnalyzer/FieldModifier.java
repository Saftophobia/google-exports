package syntaxAnalyzer;

public class FieldModifier{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " public " : return true;
		case " protected " : return true;
		case " private " : return true;
		case " static " : return true;
		case " final " : return true;
		case " transient " : return true;
		case " volatile" : return true;
		default : return false;
	}
}
}
