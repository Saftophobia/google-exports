package parser;

import sentence.ConnectedSentence;
import sentence.NotSentence;
import sentence.Predicate;
import sentence.QuantifiedSentence;
import sentence.TermEquality;
import term.Constant;
import term.Function;
import term.Variable;


// this an interface used to determine the behavior of the terms and the sentences based on
// the problem context the methods below are too generic they are taking an Object input 
// and output an Object class. Using these method we can visit a sentence or part of it 
// and evaluate something depending on what is needed in any problem
public interface FOLVisitor {
	// This is the method that determine the behavior of a predicate when visited
	public Object visitPredicate(Predicate p, Object arg);
	// This is the method that determine the behavior of a equality sentence when visited
	public Object visitTermEquality(TermEquality equality, Object arg);
	// This is the method that determine the behavior of a variable when visited
	public Object visitVariable(Variable variable, Object arg);
	// This is the method that determine the behavior of a constant when visited
	public Object visitConstant(Constant constant, Object arg);
	// This is the method that determine the behavior of a function when visited
	public Object visitFunction(Function function, Object arg);
	// This is the method that determine the behavior of a negated sentence when visited
	public Object visitNotSentence(NotSentence sentence, Object arg);
	// This is the method that determine the behavior of connectedSentence when visited
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg);
	// This is the method that determine the behavior of qualified sentence when visited
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg);
}
