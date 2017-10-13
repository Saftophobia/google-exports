package parser;


// A token is the conversion of a char in a string to a data structure 
public class Token {
	// it consists of a text or value of the original char
	private String text;
	// a type of the text or value
	private int type;
	// here are the possible types
	
	// just a generic Symbol with id  = 1
	static final int SYMBOL = 1;
	// ( with id  = 2
	static final int LPAREN = 2;
	// ) with id  = 3
	static final int RPAREN = 3;
	// , with id  = 4
	static final int COMMA = 4;
	// connector with id  = 5
	static final int CONNECTOR = 5;
	// quantifier with id  = 6
	static final int QUANTIFIER = 6;
	// predicate with id  = 7
	static final int PREDICATE = 7;
	// function with id  = 8
	static final int FUNCTION = 8;
	// variable with id  = 9
	static final int VARIABLE = 9;
	// constant with id  = 10
	static final int CONSTANT = 10;
	// true with id  = 11
	static final int TRUE = 11;
	// false with id  = 12
	static final int FALSE = 12;
	// = with id  = 13
	static final int EQUALS = 13;
	// " " with id  = 1000
	static final int WHITESPACE = 1000;
	// End of input with id  = 9999
	static final int EOI = 9999;
	
	
	 
	//Constructs a token from the specified token-name and attribute-value
	public Token(int type, String text) {
		// set the type
		this.type = type;
		// set the text or value
		this.text = text;
	}


	// Returns the value of this token.
	public String getText() {
		return text;
	}

	
	// Returns the token-name of this token.
	public int getType() {
		return type;
	}

	
	// overriding the equals methods so that 
	//we can compare 2 different token based on
	//the the type and the text (value) 
	@Override
	public boolean equals(Object o) {
		// if it is the same instance
		if (this == o) {
			return true;
		}
		// o = null  then compare based on the class type
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		// comparing two different objects
		Token other = (Token) o;
		return ((other.type == type) && (other.text.equals(text)));
	}

	
	// Overriding the HashCode function so that we can use 
	//it as a unique value in Hash - map - set - table
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + type;
		result = 37 * result + text.hashCode();
		return 17;
	}

	// a toString method to represent the token
	@Override
	public String toString() {
		return "[ " + type + " " + text + " ]";
	}
	
}