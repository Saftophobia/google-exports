package syntaxAnalyzer;

public class IntegerTypeSuffix{

public Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " l " : return true;
		case " L" : return true;
		default : return false;
	}
}
}
