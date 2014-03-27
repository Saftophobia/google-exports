import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RealCodeGenerator {

	public static void main(String[] args) throws IOException {
		InputStream grammar = new FileInputStream("src/Grammar.txt");
		BufferedReader grammarReader = new BufferedReader(
				new InputStreamReader(grammar));

		String line = "";
		PrintWriter writer = new PrintWriter(
				"src/syntaxAnalyzer/SyntaxAnalyzer.java", "UTF-8");
		writer.println("package syntaxAnalyzer;");
		writer.println();
		writer.println("public class SyntaxAnalyzer {");
		writer.println("\tpublic static Tokenizer tokenizer = new Tokenizer();");
		while ((line = grammarReader.readLine()) != null) {
			String methodName = line.split("::=")[0];
			methodName = getMethodName(methodName);
			writer.println("\tpublic static boolean " + methodName
					+ "(Token tok){");
			writer.println("\tint oldTokenCount = tokenizer.index;");
			switch (methodName) {
			case "Keyword":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "BooleanLiteral":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "NullLiteral":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "NonZeroDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "HexDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "OctalDigit":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ExponentIndicator":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "IntegerTypeSuffix":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "AssignmentOperator": // TODO
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ClassModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "InterfaceModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "FieldModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ConstantModifiers":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "ConstructorModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "MethodModifier":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "IntegralType":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "FloatingPointType":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "EmptyStatement":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "Sign":
				terminalCase(writer, line.split("::=")[1]);
				break;
			case "SingleTypeImportDeclaration":
				sequencing(writer, line.split("::=")[1],false,false);
				break;	
			case "PackageDeclaration":
				sequencing(writer, line.split("::=")[1],false,false);
				break;
			default:
				writer.println("\treturn false;");
			}
			writer.println("\t}");
		}
		writer.println("}");
		writer.close();
		grammarReader.close();
		grammar.close();
	}

	public static void terminalCase(PrintWriter writer, String line) {
		String normalized = line.replace(' ', '\0');
		writer.println("\t\tString tokenValue= tok.value;");
		writer.println("\t\tswitch(tokenValue){");
		String[] Keywords = normalized.split("\\|");
		for (int i = 0; i < Keywords.length; i++) {
			writer.println("\t\t\tcase \"" + Keywords[i] + "\" : return true;");
		}
		writer.println("\t\t\tdefault : return false;");
		writer.println("\t\t}");
	}

	public static void variableCase(PrintWriter writer, String line) {
		writer.println("int oldTokenCount = tokenizer.index;");
		if (line.contains("|")) {
		//	String[] Keywords = line.split("\\|");

			//for (int i = 0; i < Keywords.length; i++) {
//				if(i+1 == Keywords.length)
//				sequencing(writer, Keywords[i],true,true);
//				else
//				sequencing(writer, Keywords[i],true,false);	
			//}

		} else {
		//	sequencing(writer, line,false,false);
		}
	}

	public static void sequencing(PrintWriter writer, String line,boolean isAlternating,boolean lastAlternating) {

		ArrayList<String> tokens = tokenize(line);
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> classesWithOneOrZero = new ArrayList<State>();
		ArrayList<State> classesWithOnlyOne = new ArrayList<State>();
		for (String s : tokens) {
			if (s.startsWith("<") && s.length() > 1) {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					ClassHolder cls = new ClassHolder();
					cls.name = getMethodName(normalized);
					cls.oneOrzero = true;
					states.add(cls);
					classesWithOneOrZero.add(cls);
				} else {
					ClassHolder cls = new ClassHolder();
					cls.name = getMethodName(s);
					cls.oneOrzero = false;
					states.add(cls);
					classesWithOnlyOne.add(cls);
				}
			} else {
				if (s.endsWith("?")) {
					String normalized = s.substring(0, s.length() - 1);
					Token t = new Token();
					t.token = normalized;
					t.oneOrzero = true;
					states.add(t);
					classesWithOneOrZero.add(t);
				} else {
					Token t = new Token();
					t.token = s;
					t.oneOrzero = false;
					states.add(t);
					classesWithOnlyOne.add(t);
				}

			}
		}

		int lastInteration = states.size() - 1;
		for (int i = 0; i < states.size(); i++) {
			if (i == 0 || i == lastInteration) {
				if (states.get(i) instanceof ClassHolder) {
					writer.println("if (" + states.get(i) + "(tok)) {");
					if (i != lastInteration) {
						writer.println("tok = tokenizer.getNextToken();");
						writer.println("} else {");
						writer.println("return false;");
						writer.println("}");
					} else {
						if (i != 0 || lastAlternating) {
							writer.println("return true;");
						} else {
							writer.println("tok = tokenizer.getNextToken();");
						}
						writer.println("} else {");
						if (i != 0) {
							writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
						} else {
							writer.println("return false;");
						}
						writer.println("}");
					}

				} else {
					writer.println("if (tok.value.equals((\"" + states.get(i)
							+ "\"))) {");
					if (i != lastInteration) {
						writer.println("tok = tokenizer.getNextToken();");
						writer.println("} else {");
						writer.println("return false;");
						writer.println("}");
					} else {
						if (i != 0 || lastAlternating) {
							writer.println("return true;");
						} else {
							writer.println("tok = tokenizer.getNextToken();");
						}
						writer.println("} else {");
						if (i != 0) {
							writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
						} else {
							writer.println("return false;");
						}
						writer.println("}");
					}
				}

			} else {
				if (states.get(i) instanceof ClassHolder) {
					writer.println("if (" + states.get(i) + "(tok)) {");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("} else {");
					writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					writer.println("}");
				} else {
					writer.println("if (tok.value.equals((\"" + states.get(i)
							+ "\"))) {");
					writer.println("tok = tokenizer.getNextToken();");
					writer.println("} else {");
					writer.println("return tokenizer.pushBack(tokenizer.index - oldTokenCount );");
					writer.println("}");
				}
			}
		}
	}

	public static ArrayList<String> tokenize(String s) {
		boolean seenAngluarBracket = false;
		ArrayList<String> tokens = new ArrayList<String>();
		String currentToken = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ' && !seenAngluarBracket) {
				if (currentToken.length() > 0) {
					currentToken = currentToken.replace("$", "\\\"");
					tokens.add(currentToken);
					currentToken = "";
					continue;
				} else {
					continue;
				}
			}
			if (s.charAt(i) == '<') {
				seenAngluarBracket = true;
				currentToken += "<";
				continue;
			}
			if (s.charAt(i) == '>') {
				seenAngluarBracket = false;
				currentToken += ">";
				if (i + 1 < s.length() && s.charAt(i + 1) == '?') {
					currentToken += "?";
					i++;
				}
				tokens.add(currentToken);
				currentToken = "";
				continue;
			}

			currentToken += s.charAt(i);
		}
		if (currentToken.length() > 0) {
			tokens.add(currentToken);
		}
		return tokens;
	}

	public static String getMethodName(String s) {
		String out = "";
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != '>')
				out += s.charAt(i);
			else
				break;
		}
		s = out;
		if (s.contains(" ")) {
			String[] temp = s.split(" ");
			s = "";
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].contains("-")) {
					String[] temp2 = temp[i].split("-");
					for (int j = 0; j < temp2.length; j++) {
						s += Character.toUpperCase(temp2[j].charAt(0))
								+ temp2[j].substring(1);
					}
				} else {
					s += Character.toUpperCase(temp[i].charAt(0))
							+ temp[i].substring(1);
				}
			}
		} else {
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
		}
		return s;
	}

	static abstract class State {

	}

	static class Token extends State {
		public String token;
		public boolean oneOrzero;

		public String toString() {
			return token;
		}
	}

	static class ClassHolder extends State {
		public String name;
		public boolean oneOrzero;

		public String toString() {
			return name;
		}
	}
}
