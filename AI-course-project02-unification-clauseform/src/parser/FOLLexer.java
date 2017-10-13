package parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

import utility.SyncategorematicSymbols;


// This class is used to recognize the FOL syntax it represents 
// every symbol that can be used to describe a FOL sentence
// it consists of the FOL symbols (Syncategorematics Symbols) 
// in addition to the Domain defined by the problem
// LEXER = Domain + Syncategorematics Symbols
// Its main role is to determine the symbols 
//of the Domain and Syncategorematics Symbols in any String and return 
//the corresponding tokens for that String

public class FOLLexer {
	// The Domain
	private FOLDomain domain;
	// Two sets for the Syncategorematics Symbols (connectors and quantifiers)
	private Set<String> connectors, quantifiers;
	// A reader that is used to read the input String used in the  extraction of the symbols from it later
	private Reader input;
	// A Size for the buffer
	private int lookAhead = 1;
	// A buffer used to hold a character that need to converted into token.
	private int[] lookAheadBuffer;
	
	// the Constructor of the FOLLexer is taking as a parameter a FOLDomain instance
	public FOLLexer(FOLDomain domain) {
		// set the domain to the domain variable.
		this.domain = domain;
		// add the FOL Syncategorematics Symbols
		
		// First we begin by adding the connectors connectors Set
		// initialize the connectors HashSet.
		connectors = new HashSet<String>();
		// Adding the NOT connector
		connectors.add(SyncategorematicSymbols.NOT);
		// Adding the AND connector
		connectors.add(SyncategorematicSymbols.AND);
		// Adding the OR connector
		connectors.add(SyncategorematicSymbols.OR);
		// Adding the IMPLIES connector
		connectors.add(SyncategorematicSymbols.IMPLIES);
		// Adding the BICOND connector
		connectors.add(SyncategorematicSymbols.BICOND);

		// Second we add the Quantifiers
		// initialize the quantifiers HashSet
		quantifiers = new HashSet<String>();
		// Adding the FORALL quantifier
		quantifiers.add(SyncategorematicSymbols.FORALL);
		// Adding the EXISTS quantifier
		quantifiers.add(SyncategorematicSymbols.EXISTS);
	}

	
	// this method is a getter method for the domain
	public FOLDomain getFOLDomain() {
		// retiurn  the domain.
		return domain;
	}

	
	// This method is used to return a new instance of the Token object
	// The returned value is next char in a given a String that was buffered inside the lookAheadBuffer
	// EX: P(x,y) assuming that we are at the x to that the next token is the ","
	public Token nextToken() {
		// checking the next character inside the lookAheadBuffer if it is a '('
		if (lookAhead(1) == '(') {
			// remove the character from the lookAheadBuffer because we was able to determine its value
			consume();
			// return the a new Instance of the corresponding Token
			// given its type and its value.
			return new Token(Token.LPAREN, "(");
			// checking the next character inside the lookAheadBuffer if it is a ')'
		} else if (lookAhead(1) == ')') {
			// remove the character from the lookAheadBuffer because we was able to determine its value
			consume();
			// return the a new Instance of the corresponding Token
			// given its type and its value.
			return new Token(Token.RPAREN, ")");
			// checking the next character inside the lookAheadBuffer if it is a ','
		} else if (lookAhead(1) == ',') {
			// remove the character from the lookAheadBuffer because we was able to determine its value
			consume();
			// return the a new Instance of the corresponding Token
			// given its type and its value.
			return new Token(Token.COMMA, ",");
			// checking the next character inside the lookAheadBuffer if it is a normal char or part of connector
		} else if (identifierDetected()) {
			// System.out.println("identifier detected");
			// call the identifier method to deal with it
			return identifier();
			// if it is a white space just consume it 
		} else if (Character.isWhitespace(lookAhead(1))) {
			// remove the character from the lookAheadBuffer because we was able to determine its value
			consume();
			// just redo till finding a real token.
			return nextToken();
			// if we reached the -1 this mean we have reached the end of the input
			// just return the EOI token
		} else if (lookAhead(1) == (char) -1) {
			// return the EOI token
			return new Token(Token.EOI, "EOI");
		} else {
			// if any token is received other than the specified above just return an exception 
			throw new RuntimeException("Lexing error on character "
					+ lookAhead(1));
		}
	}

	// This method is responsible of returning a token instance 
	//in case of the identifier check succeeded
	private Token identifier() {
		// Creating new StringBuffer to append to it the identifier chars
		StringBuffer sbuf = new StringBuffer();
		// this line says that append till you find a space or a non part of connector or javaIdentifierPart
		// in normal cases the only thing that stops this loop is the empty space
		while ((Character.isJavaIdentifierPart(lookAhead(1)))
				|| partOfConnector()) {
			// append this character to the buffer
			sbuf.append(lookAhead(1));
			// delete the current charcter and load an new one.
			consume();
		}
		// convert the buffer into a String to be used.
		String readString = new String(sbuf);
		// System.out.println(readString);
		// if the connectors contains the readString
		if (connectors.contains(readString)) {
			// return a new corresponding connector token
			return new Token(Token.CONNECTOR, readString);
			// if the quantifiers contains the readString
		} else if (quantifiers.contains(readString)) {
			// return a new corresponding Quantifier token
			return new Token(Token.QUANTIFIER, readString);
			// if the domain predicates contains the readString
		} else if (domain.getPredicates().contains(readString)) {
			// return a new corresponding predicate token
			return new Token(Token.PREDICATE, readString);
			// if the domain functions contains the readString
		} else if (domain.getFunctions().contains(readString)) {
			// return a new corresponding function token
			return new Token(Token.FUNCTION, readString);
			// if the domain constants contains the readString
		} else if (domain.getConstants().contains(readString)) {
			// return a new corresponding constant token
			return new Token(Token.CONSTANT, readString);
			// if readString is a variable
		} else if (isVariable(readString)) {
			// return a new corresponding variable token
			return new Token(Token.VARIABLE, readString);
			// if readString is the = sign
		} else if (readString.equals("=")) {
			// return a new corresponding equals token
			return new Token(Token.EQUALS, readString);
		} else {
			// in case if no matching case this mean that there is something wrong
			throw new RuntimeException("Lexing error on character "
					+ lookAhead(1));
		}

	}

	// This method check if the input String first character is a variable 
	private boolean isVariable(String s) {
		// the check is done by seeing if the the character is a lower case or not
		return (Character.isLowerCase(s.charAt(0)));
	}

	// This method is used to differenciate between the symbols like "(,) and ," 
	//and normal letters on addition to part of connectors like "=>"
	private boolean identifierDetected() {
		return (Character.isJavaIdentifierStart((char) lookAheadBuffer[0]))
				|| partOfConnector();
	}

	// This method check if the string in the current lookAhead index is part of a connector
	// => consists of 2 parts the "=" and the ">"
	//<=> consists of 3 parts the "<", "=" and ">"
	// that's why we need a check to know if the current lookAhead is a part of connector or not
	private boolean partOfConnector() {
		// check if the current look ahead is equal to "=", "<" and ">"
		return (lookAhead(1) == '=') || (lookAhead(1) == '<')
				|| (lookAhead(1) == '>');
	}
	
	
	 // Sets the character stream of the lexical analyzer
	 // inputString a sequence of characters to be converted into a sequence of tokens
	public void setInput(String inputString) {
		// create a new lookAheadBuffer with the lookAhead size (1)
		lookAheadBuffer = new int[lookAhead];
		// setting the input reader to the input String
		this.input = new StringReader(inputString);
		// filling the buffer from the input
		fillLookAheadBuffer();
	}

	
	// Sets the character stream and look ahead buffer to null
	// to help the garbage collector
	public void clear() {
		this.input = null;
		lookAheadBuffer = null;
	}

	
	 //Stores the next character in the lookahead buffer to make parsing action
	 //decisions.
	protected void fillLookAheadBuffer() {
		// because in our case the lookAheadBuffer is of size 1
		// only the char at index 0 is only set
		try {
			lookAheadBuffer[0] = (char) input.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	// Returns the character at the specified position in the lookahead buffer.
	// in our case it just return the char at position 0
	protected char lookAhead(int position) {
		return (char) lookAheadBuffer[position - 1];
	}

	
	// Returns true if the end of the stream has been reached.
	// the StringReader when reach the end of a string it returns -1
	// the check done here is based on that
	protected boolean isEndOfFile(int i) {
		return (-1 == i);
	}

	
	 // Loads the next character into the lookahead buffer if the end of the
	 // stream has not already been reached.
	protected void loadNextCharacterFromInput() {

		// End of File flag is set to false at the beginning
		boolean eofEncountered = false;
		// A loop on the buffer in our case the lookAhead is set to 1 so the loop will never be accessed
		for (int i = 0; i < lookAhead - 1; i++) {
			// shifts the lookAheadBuffer and deleting the first Char
			lookAheadBuffer[i] = lookAheadBuffer[i + 1];
			// check if we have reached the end of the input so no more tokens to return
			// if the end of input is reached the eofEncoutered is set to true and the loop
			// is broke
			if (isEndOfFile(lookAheadBuffer[i])) {
				eofEncountered = true;
				break;
			}
		}
		// if we haven't reached the end of the input 
		// append to the end a new character so that it can be converted into token/
		if (!eofEncountered) {
			try {
				lookAheadBuffer[lookAhead - 1] = input.read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// shifts the buffer to its beginning 
	// adding at the end a new char
	// deleting the the one at the beginning
	protected void consume() {
		// just call loadNextCharachter
		loadNextCharacterFromInput();
	}
	
}