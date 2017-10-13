package clause;

import java.util.LinkedHashSet;
import java.util.Set;

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
import utility.FOLVisitor;
import abstracts.Chain;
import abstracts.Clause;
import abstracts.Literal;

/**
 *
 */
public class VariableCollector implements FOLVisitor {

	public VariableCollector() {
	}

	// The set guarantees the order in which they were
	// found. it gets all variables in the correct order given the sentence
	public Set<Variable> collectAllVariables(Sentence sentence) {
		Set<Variable> variables = new LinkedHashSet<Variable>();

		sentence.accept(this, variables);

		return variables;
	}

	// The set guarantees the order in which they were
	// found. it gets all variables in the correct order given the term
	public Set<Variable> collectAllVariables(Term term) {
		Set<Variable> variables = new LinkedHashSet<Variable>();

		term.accept(this, variables);

		return variables;
	}

	// The set guarantees the order in which they were
	// found. it gets all variables in the correct order given the clause
	public Set<Variable> collectAllVariables(Clause clause) {
		Set<Variable> variables = new LinkedHashSet<Variable>();

		for (Literal l : clause.getLiterals()) {
			l.getAtomicSentence().accept(this, variables);
		}

		return variables;
	}
	// The set guarantees the order in which they were
	// found. it gets all variables in the correct order given the chain
	public Set<Variable> collectAllVariables(Chain chain) {
		Set<Variable> variables = new LinkedHashSet<Variable>();

		for (Literal l : chain.getLiterals()) {
			l.getAtomicSentence().accept(this, variables); 
		}

		return variables;
	}
	//variable reader
	@SuppressWarnings("unchecked")
	public Object visitVariable(Variable var, Object arg) {
		Set<Variable> variables = (Set<Variable>) arg; //clone arg
		variables.add(var); //add variable to list of variables
		return var;
	}
	
	//quantified sentence reader
	@SuppressWarnings("unchecked")
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// Ensure I collect quantified variables too
		Set<Variable> variables = (Set<Variable>) arg; //clone arg 
		variables.addAll(sentence.getVariables()); //add sentence variable to the list of variables

		sentence.getQuantified().accept(this, arg); //decide behavior of the qsentence,change it and return a copy

		return sentence;
	}
	//predicate reader
	public Object visitPredicate(Predicate predicate, Object arg) {
		for (Term t : predicate.getTerms()) {
			t.accept(this, arg);  //check every term in the predicate for unification purposes  
		}
		
		return predicate;
	}
	//determine the behavior of a equality sentence when visited
	public Object visitTermEquality(TermEquality equality, Object arg) {
		equality.getTerm1().accept(this, arg);
		equality.getTerm2().accept(this, arg);
		return equality;
	}
	//constant reader
	public Object visitConstant(Constant constant, Object arg) {
		return constant;
	}
	//function reader
	public Object visitFunction(Function function, Object arg) {
		for (Term t : function.getTerms()) {
			t.accept(this, arg);//check every term in the function for unification purposes
		}
		return function;
	}
	////determine the behavior of a negated sentence when visited
	public Object visitNotSentence(NotSentence sentence, Object arg) {
		sentence.getNegated().accept(this, arg);
		return sentence;
	}
	//determine the behavior of connectedSentence when visited
	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg) {
		sentence.getFirst().accept(this, arg);
		sentence.getSecond().accept(this, arg);
		return sentence;
	}
}