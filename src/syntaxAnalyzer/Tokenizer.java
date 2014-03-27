package syntaxAnalyzer;

import java.util.ArrayList;

public class Tokenizer {

	ArrayList<Token> tokens = new ArrayList<Token>();
	int index = 0;

	public Tokenizer() {

	}

	public Token getCurrentToken() {
		if (index < tokens.size()) {
			return tokens.get(index++);
		} else {
			return null;
		}
	}
	
	public Token getNextToken() {
		if (index < tokens.size()) {
			return tokens.get(++index);
		} else {
			return null;
		}
	}

	public boolean pushBack(int delta) {

		if (index >= delta)
			index -= delta;
		else
			index = 0;

		return false;
	}

}
