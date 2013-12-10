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

// this class is just an abstract Visitor class 
//showing a way of interactions with the sentence and terms
// most of the behavior is just copying the sentence given.
public class AbstractFOLVisitor implements FOLVisitor {

	// empty constructor
	public AbstractFOLVisitor() {
	}

	// a method to copy a sentence
	protected Sentence recreate(Object ast) {
		return ((Sentence) ast).copy();
	}

	// the behavior of visiting a variable is just returning a copy of it.
	public Object visitVariable(Variable variable, Object arg) {
		return variable.copy();
	}

	// the behavior of visiting a Quantified Sentence is just returning a new copy of it
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// creating a new variable list
		List<Variable> variables = new ArrayList<Variable>();
		for (Variable var : sentence.getVariables()) {
			// call the accept method of each variable in the sentence 
			//so that the variable call its visit method above and return a copy of it
			variables.add((Variable) var.accept(this, arg));
		}
		// creating a new copy of the quantified sentence
		return new QuantifiedSentence(sentence.getQuantifier(), variables,
				(Sentence) sentence.getQuantified().accept(this, arg));
	}
	// this method return a copy of the predicate sentence
	public Object visitPredicate(Predicate predicate, Object arg) {
		// get the current predicate terms
		List<Term> terms = predicate.getTerms();
		// create a new list to holds the copies of the terms
		List<Term> newTerms = new ArrayList<Term>();
		// coping the terms
		for (int i = 0; i < terms.size(); i++) {
			// getting term t
			Term t = terms.get(i);
			// getting term t copy
			Term subsTerm = (Term) t.accept(this, arg);
			// add the copy to the new list
			newTerms.add(subsTerm);
		}
		// return a new copy 
		return new Predicate(predicate.getPredicateName(), newTerms);

	}

	// this method return a copy of the the equality sentence
	public Object visitTermEquality(TermEquality equality, Object arg) {
		// copy the first term
		Term newTerm1 = (Term) equality.getTerm1().accept(this, arg);
		// copy the second term
		Term newTerm2 = (Term) equality.getTerm2().accept(this, arg);
		// creating a new copy of both terms
		return new TermEquality(newTerm1, newTerm2);
	}

	// this method just return the given constant as it is
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}

	// this method returns a copy of the given function
	public Object visitFunction(Function function, Object arg) {
		// get the functions terms
		List<Term> terms = function.getTerms();
		// create a new instance to hold the copy of the terms 
		List<Term> newTerms = new ArrayList<Term>();
		// copy the terms one by one
		for (int i = 0; i < terms.size(); i++) {
			// getting term t
			Term t = terms.get(i);
			// getting term t copy
			Term subsTerm = (Term) t.accept(this, arg);
			// add the copy to the new list
			newTerms.add(subsTerm);
		}
		// return a new copy instance of the function
		return new Function(function.getFunctionName(), newTerms);
	}

	// this method return a copy of the given negated sentence
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		// a copy of the negated sentence is returned
		return new NotSentence((Sentence) sentence.getNegated().accept(this,
				arg));
	}

	// this method returns a copy of the given connected sentence
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		// copy the first sentence
		Sentence substFirst = (Sentence) sentence.getFirst().accept(this, arg);
		// copy the second sentence
		Sentence substSecond = (Sentence) sentence.getSecond()
				.accept(this, arg);
		// return a new copy of the connected sentence using the first and the second copies
		return new ConnectedSentence(sentence.getConnector(), substFirst,
				substSecond);
	}
}