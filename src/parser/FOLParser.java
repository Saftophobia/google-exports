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
		// calling the above constructor with a new lexer instance created using the domain
		this(new FOLLexer(domain));
	}

	// a domain getter
	public FOLDomain getFOLDomain() {
		return lexer.getFOLDomain();
	}

	// This method is used for parsing a String into a sentence
	public Sentence parse(String s) {
		// it calls 
		setUpToParse(s);
		return parseSentence();
	}
	
	//
	public void setUpToParse(String s) {
		lexer.clear();
		lookAheadBuffer = new Token[1];
		lexer.setInput(s);
		fillLookAheadBuffer();

	}

	private Term parseTerm() {
		Token t = lookAhead(1);
		int tokenType = t.getType();
		if (tokenType == Token.CONSTANT) {
			return parseConstant();
		} else if (tokenType == Token.VARIABLE) {
			return parseVariable();
		} else if (tokenType == Token.FUNCTION) {
			return parseFunction();
		}

		else {
			return null;
		}
	}

	public Term parseVariable() {
		Token t = lookAhead(1);
		String value = t.getText();
		consume();
		return new Variable(value);
	}

	public Term parseConstant() {
		Token t = lookAhead(1);
		String value = t.getText();
		consume();
		return new Constant(value);
	}

	public Term parseFunction() {
		Token t = lookAhead(1);
		String functionName = t.getText();
		List<Term> terms = processTerms();
		return new Function(functionName, terms);
	}

	public Sentence parsePredicate() {
		Token t = lookAhead(1);
		String predicateName = t.getText();
		List<Term> terms = processTerms();
		return new Predicate(predicateName, terms);
	}

	private List<Term> processTerms() {
		consume();
		List<Term> terms = new ArrayList<Term>();
		match("(");
		Term term = parseTerm();
		terms.add(term);

		while (lookAhead(1).getType() == Token.COMMA) {
			match(",");
			term = parseTerm();
			terms.add(term);
		}
		match(")");
		return terms;
	}

	public Sentence parseTermEquality() {
		Term term1 = parseTerm();
		match("=");
		// System.out.println("=");
		Term term2 = parseTerm();
		return new TermEquality(term1, term2);
	}

	public Sentence parseNotSentence() {
		match("NOT");
		return new NotSentence(parseSentence());
	}

	//
	// PROTECTED METHODS
	//
	protected Token lookAhead(int i) {
		return lookAheadBuffer[i - 1];
	}

	protected void consume() {
		// System.out.println("consuming" +lookAheadBuffer[0].getText());
		loadNextTokenFromInput();
		// System.out.println("next token " +lookAheadBuffer[0].getText());
	}

	protected void loadNextTokenFromInput() {

		boolean eoiEncountered = false;
		for (int i = 0; i < lookAhead - 1; i++) {

			lookAheadBuffer[i] = lookAheadBuffer[i + 1];
			if (isEndOfInput(lookAheadBuffer[i])) {
				eoiEncountered = true;
				break;
			}
		}
		if (!eoiEncountered) {
			try {
				lookAheadBuffer[lookAhead - 1] = lexer.nextToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected boolean isEndOfInput(Token t) {
		return (t.getType() == Token.EOI);
	}

	protected void fillLookAheadBuffer() {
		for (int i = 0; i < lookAhead; i++) {
			lookAheadBuffer[i] = lexer.nextToken();
		}
	}

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

	private Sentence parseSentence() {
		Token t = lookAhead(1);
		if (lParen(t)) {
			return parseParanthizedSentence();
		} else if ((lookAhead(1).getType() == Token.QUANTIFIER)) {

			return parseQuantifiedSentence();
		} else if (notToken(t)) {
			return parseNotSentence();
		} else if (predicate(t)) {
			return parsePredicate();
		} else if (term(t)) {
			return parseTermEquality();
		}

		throw new RuntimeException("parse failed with Token " + t.getText());
	}

	private Sentence parseQuantifiedSentence() {
		String quantifier = lookAhead(1).getText();
		consume();
		List<Variable> variables = new ArrayList<Variable>();
		Variable var = (Variable) parseVariable();
		variables.add(var);
		while (lookAhead(1).getType() == Token.COMMA) {
			consume();
			var = (Variable) parseVariable();
			variables.add(var);
		}
		Sentence sentence = parseSentence();
		return new QuantifiedSentence(quantifier, variables, sentence);
	}

	private Sentence parseParanthizedSentence() {
		match("(");
		Sentence sen = parseSentence();
		while (binaryConnector(lookAhead(1))) {
			String connector = lookAhead(1).getText();
			consume();
			Sentence other = parseSentence();
			sen = new ConnectedSentence(connector, sen, other);
		}
		match(")");
		return sen; /* new ParanthizedSentence */

	}

	private boolean binaryConnector(Token t) {
		if ((t.getType() == Token.CONNECTOR)
				&& (!(t.getText().equals("NOT")))) {
			return true;
		} else {
			return false;
		}
	}

	private boolean lParen(Token t) {
		if (t.getType() == Token.LPAREN) {
			return true;
		} else {
			return false;
		}
	}

	private boolean term(Token t) {
		if ((t.getType() == Token.FUNCTION)
				|| (t.getType() == Token.CONSTANT)
				|| (t.getType() == Token.VARIABLE)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean predicate(Token t) {
		if ((t.getType() == Token.PREDICATE)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean notToken(Token t) {
		if ((t.getType() == Token.CONNECTOR)
				&& (t.getText().equals("NOT"))) {
			return true;
		} else {
			return false;
		}
	}
}