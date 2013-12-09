package parser;


// A token is the conversion of a char in a string to a data structure 
public class Token {
	// it consists of a text or value of the original char
	private String text;
	// a type of the text or value
	private int type;
	// here are the possible types
	static final int SYMBOL = 1;

	static final int LPAREN = 2;

	static final int RPAREN = 3;

	static final int COMMA = 4;

	static final int CONNECTOR = 5;

	static final int QUANTIFIER = 6;

	static final int PREDICATE = 7;

	static final int FUNCTION = 8;

	static final int VARIABLE = 9;

	static final int CONSTANT = 10;

	static final int TRUE = 11;

	static final int FALSE = 12;

	static final int EQUALS = 13;

	static final int WHITESPACE = 1000;

	static final int EOI = 9999;
	
	/**
	 * Constructs a token from the specified token-name and attribute-value
	 * 
	 * @param type
	 *            the token-name
	 * @param text
	 *            the attribute-value
	 */
	public Token(int type, String text) {
		this.type = type;
		this.text = text;
	}

	/**
	 * Returns the attribute-value of this token.
	 * 
	 * @return the attribute-value of this token.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns the token-name of this token.
	 * 
	 * @return the token-name of this token.
	 */
	public int getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Token other = (Token) o;
		return ((other.type == type) && (other.text.equals(text)));
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + type;
		result = 37 * result + text.hashCode();
		return 17;
	}

	@Override
	public String toString() {
		return "[ " + type + " " + text + " ]";
	}
	
}