package syntaxAnalyzer;

public class Keyword{

public static Object eval(Object o ){
	String keyword = (String) o;
	switch(keyword){
		case " abstract " : return true;
		case " boolean " : return true;
		case " break " : return true;
		case " byte " : return true;
		case " case " : return true;
		case " catch " : return true;
		case " char " : return true;
		case " class " : return true;
		case " const " : return true;
		case " continue " : return true;
		case " default " : return true;
		case " do " : return true;
		case " double " : return true;
		case " else " : return true;
		case " extends " : return true;
		case " final " : return true;
		case " finally " : return true;
		case " float " : return true;
		case " for " : return true;
		case " goto " : return true;
		case " if " : return true;
		case " implements " : return true;
		case " import " : return true;
		case " instanceof " : return true;
		case " int " : return true;
		case " interface " : return true;
		case " long " : return true;
		case " native " : return true;
		case " new " : return true;
		case " package " : return true;
		case " private " : return true;
		case " protected " : return true;
		case " public " : return true;
		case " return " : return true;
		case " short " : return true;
		case " static " : return true;
		case " super " : return true;
		case " switch " : return true;
		case " synchronized " : return true;
		case " this " : return true;
		case " throw " : return true;
		case " throws " : return true;
		case " transient " : return true;
		case " try " : return true;
		case " void " : return true;
		case " volatile " : return true;
		case " while" : return true;
		default : return false;
	}
}
}
