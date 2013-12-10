package parser;

import java.util.ArrayList;
import java.util.List;

import sentence.ConnectedSentence;
import sentence.NotSentence;
import sentence.Predicate;
import sentence.QuantifiedSentence;
import sentence.Sentence;
import sentence.TermEquality;

import term.Constant;
import term.Function;
import term.Term;
import term.Variable;

// The parser is the class responsible for reading a string 
// from the user then transforms it into a set of java objects.
public class FOLParser {
	// it has a lexer for all the symbols in FOL
	private FOLLexer lexer;
	// a lookAheadBuffer used to buffer the char to be parsed
	protected Token[] lookAheadBuffer;
	// buffer size of the lookAheadBuffer
	protected int lookAhead = 1;

	// a constructor for the parser taking only a lexer
	public FOLParser(FOLLexer lexer) {
		// setting the lexer
		this.lexer = lexer;
		// creating a new buffer with size 1
		lookAheadBuffer = new Token[lookAhead];
	}

	// a constructor for the parser taking only a domain
	public FOLParser(FOLDomain domain) {
		// calling the above constructor with a new lexer instance created using
		// the domain
		this(new FOLLexer(domain));
	}

	// a domain getter
	public FOLDomain getFOLDomain() {
		return lexer.getFOLDomain();
	}

	// This method is used for parsing a String into a sentence
	public Sentence parse(String s) {
		// it calls a helper method setUpToParse
		// to initialize the parsing enviroment
		setUpToParse(s);
		// call the actual parsing method.
		return parseSentence();
	}
	
	// This method is used for parsing a String into a term
	public Term parseTerm(String s) {
		// it calls a helper method setUpToParse
		// to initialize the parsing enviroment
		setUpToParse(s);
		// call the actual parsing method.
		return parseTerm();
	}

	// Initializing the parsing environment
	public void setUpToParse(String s) {
		// clear the lexer from old parsings
		lexer.clear();
		// reinitialize the lookAheadBuffer
		lookAheadBuffer = new Token[1];
		// setting the input string to the lexer
		lexer.setInput(s);
		// fill the buffer
		fillLookAheadBuffer();

	}

	// parsing a Term
	private Term parseTerm() {
		// getting a token from the top of the buffer without consuming
		Token t = lookAhead(1);
		// getting the type of the token
		int tokenType = t.getType();
		// check the type
		// if tokenType is constant
		if (tokenType == Token.CONSTANT) {
			// call parse constant
			return parseConstant();
			// of type is variable
		} else if (tokenType == Token.VARIABLE) {
			// call parse variable
			return parseVariable();
			// if type is function
		} else if (tokenType == Token.FUNCTION) {
			// call parse function
			return parseFunction();
		}
		// else = something is wrong
		else {
			return null;
		}
	}

	// parse a variable
	public Term parseVariable() {
		// load the token again
		Token t = lookAhead(1);
		// just take the value of the token
		String value = t.getText();
		// then consume it to delete it form the buffer
		consume();
		// return a new variable with the saved value
		return new Variable(value);
	}

	// parse a constant
	public Term parseConstant() {
		// load the token again
		Token t = lookAhead(1);
		// just take the value of the token
		String value = t.getText();
		// then consume it to delete it form the buffer
		consume();
		// return a new constant with the saved value
		return new Constant(value);
	}

	// parse a function
	public Term parseFunction() {
		// load the token again
		Token t = lookAhead(1);
		// just take the value of the token
		String functionName = t.getText();
		// then consume it to delete it form the buffer
		List<Term> terms = processTerms();
		// return a new constant with the saved value
		return new Function(functionName, terms);
	}

	// parse a predicate
	public Sentence parsePredicate() {
		// load the token again
		Token t = lookAhead(1);
		// just take the value of the token
		String predicateName = t.getText();
		// here we do not consume the predicate because we need to process its
		// terms
		List<Term> terms = processTerms();
		// return a new predicate with the saved value and the terms
		return new Predicate(predicateName, terms);
	}

	// process the terms of a predicate
	private List<Term> processTerms() {
		// EX: P(x,y,z)
		// here we consume the predicate token
		consume();
		// EX: (x,y,z)
		// create a new terms list
		List<Term> terms = new ArrayList<Term>();
		// we are just removing the left bracket after verifying it
		match("(");
		// EX: x,y,z)
		// then we parse the first term after the bracket
		Term term = parseTerm();
		// we add that term
		terms.add(term);
		// check for other terms based on the commas
		// EX: ,y,z)
		while (lookAhead(1).getType() == Token.COMMA) {
			// removing the "," after verifying it
			match(",");
			// EX: y,z)
			// then we parse the first term after ","
			term = parseTerm();
			// we add that term
			terms.add(term);
		}
		// then removing at the end the right bracket
		// EX: )
		match(")");
		// returns the list of the terms
		return terms;
	}

	// parsing sentence on the from s1 = s2
	public Sentence parseTermEquality() {
		// parsing the first term
		Term term1 = parseTerm();
		// removing the "=" after verifying it
		match("=");
		// System.out.println("=");
		// parsing the second term
		Term term2 = parseTerm();
		// return new term equality sentence
		return new TermEquality(term1, term2);
	}

	// parsing a negated sentence
	public Sentence parseNotSentence() {
		// removing the "NOT" after verifying it
		match("NOT");
		// then returning a new NotSentence instance
		return new NotSentence(parseSentence());
	}

	//
	// PROTECTED METHODS
	//
	// Returns the character at the specified position in the lookahead buffer.
	// in our case it just return the char at position 0
	protected Token lookAhead(int i) {
		return lookAheadBuffer[i - 1];
	}

	// shifts the buffer to its beginning
	// adding at the end a new char
	// deleting the the one at the beginning
	protected void consume() {
		// System.out.println("consuming" +lookAheadBuffer[0].getText());
		loadNextTokenFromInput();
		// System.out.println("next token " +lookAheadBuffer[0].getText());
	}

	// Loads the next token into the lookahead buffer if the end of the
	// stream has not already been reached.
	protected void loadNextTokenFromInput() {

		// End of File flag is set to false at the beginning
		boolean eoiEncountered = false;
		// A loop on the buffer in our case the lookAhead is set to 1 so the
		// loop will never be accessed
		for (int i = 0; i < lookAhead - 1; i++) {
			// shifts the lookAheadBuffer and deleting the first token
			lookAheadBuffer[i] = lookAheadBuffer[i + 1];
			// check if we have reached the end of the input so no more tokens
			// to return
			// if the end of input is reached the eofEncoutered is set to true
			// and the loop
			// is broke
			if (isEndOfInput(lookAheadBuffer[i])) {
				eoiEncountered = true;
				break;
			}
		}
		// if we haven't reached the end of the input
		// append to the end a new character so that it can be converted into
		// token/
		if (!eoiEncountered) {
			try {
				lookAheadBuffer[lookAhead - 1] = lexer.nextToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// check if we have reached the last token which is marked with the name EOI
	protected boolean isEndOfInput(Token t) {
		return (t.getType() == Token.EOI);
	}

	// filling the whole buffer with tokens
	protected void fillLookAheadBuffer() {
		// fill the buffer in a for loop
		for (int i = 0; i < lookAhead; i++) {
			// calling the lexer to generate the token
			lookAheadBuffer[i] = lexer.nextToken();
		}
	}

	//
	protected void match(String terminalSymbol) {
		if (lookAhead(1).getText().equals(terminalSymbol)) {
			consume();
		} else {
			throw new RuntimeException(
					"Syntax error detected at match. Expected "
							+ terminalSymbol + " but got "
							+ lookAhead(1).getText());
		}

	}

	//
	// PRIVATE METHODS
	//

	// this method is used to parse a sentence
	private Sentence parseSentence() {
		// first a token is load
		Token t = lookAhead(1);
		// if the token = ")" then call parse paranthizedSentence
		if (lParen(t)) {
			return parseParanthizedSentence();
			// if the token is of type Quantifier then call parseQuantified
		} else if ((lookAhead(1).getType() == Token.QUANTIFIER)) {
			return parseQuantifiedSentence();
			// if the token is the negation token then call parse not Sentence
		} else if (notToken(t)) {
			return parseNotSentence();
			// if the token is a predicate Symbol then call parse predicate
		} else if (predicate(t)) {
			return parsePredicate();
			// if the token is a generic term then just call parse term equality
		} else if (term(t)) {
			return parseTermEquality();
		}
		// else something is wrong
		throw new RuntimeException("parse failed with Token " + t.getText());
	}

	// parsing a quantified sentence
	private Sentence parseQuantifiedSentence() {
		// EX: FORALL x,y,z
		// get the token on the top of the buffer and get its value
		String quantifier = lookAhead(1).getText();
		// delete the token from the buffer
		consume();
		// EX: x,y,z
		// create a variable list
		List<Variable> variables = new ArrayList<Variable>();
		// then parse the variables behind the quantifier
		Variable var = (Variable) parseVariable();
		// add the variable
		variables.add(var);
		// add extra variables after the Comma
		// EX: ,y,z
		while (lookAhead(1).getType() == Token.COMMA) {
			// delete the comma
			consume();
			// EX: y,z
			// parse the first variable after the comma
			var = (Variable) parseVariable();
			// adding the variable
			variables.add(var);
		}
		// parsing the sentence after the FORALL x,y,z (S)
		Sentence sentence = parseSentence();
		// return a new quantified sentence
		return new QuantifiedSentence(quantifier, variables, sentence);
	}

	// parsing a paranthized sentence
	private Sentence parseParanthizedSentence() {
		// EX : (A AND B)
		// delete the left bracket
		match("(");
		// EX : A AND B)
		// then parse a normal sentence
		Sentence sen = parseSentence();
		// check for binary connector
		// EX : AND B)
		while (binaryConnector(lookAhead(1))) {
			// extract the connector
			String connector = lookAhead(1).getText();
			// then delete it from the buffer
			consume();
			// EX : B)
			// parse the other sentence
			Sentence other = parseSentence();
			// then crate a new sentence using sen and the other sentence
			sen = new ConnectedSentence(connector, sen, other);
			// EX : )
		}
		// just remove the right bracket
		match(")");
		// return the connected sentence
		return sen; /* new ParanthizedSentence */

	}

	// check if this token is a binary connector
	private boolean binaryConnector(Token t) {
		// if the type of the token is connector and its value is not a "NOT"
		// then return true
		if ((t.getType() == Token.CONNECTOR) && (!(t.getText().equals("NOT")))) {
			return true;
		} else {
			return false;
		}
	}

	// check if this token is the (
	private boolean lParen(Token t) {
		if (t.getType() == Token.LPAREN) {
			return true;
		} else {
			return false;
		}
	}

	// check if this token is term
	private boolean term(Token t) {
		// a term is either a function, a constant or a variable
		if ((t.getType() == Token.FUNCTION) || (t.getType() == Token.CONSTANT)
				|| (t.getType() == Token.VARIABLE)) {
			return true;
		} else {
			return false;
		}

	}

	// check if this token is a predicate
	private boolean predicate(Token t) {
		// if the token type is predicate then return true
		if ((t.getType() == Token.PREDICATE)) {
			return true;
		} else {
			return false;
		}
	}

	// check if this token is the not sign the oposite of binaryConnector
	private boolean notToken(Token t) {
		// if the type of the token is connector and its value is a "NOT"
		// then return true
		if ((t.getType() == Token.CONNECTOR) && (t.getText().equals("NOT"))) {
			return true;
		} else {
			return false;
		}
	}
}