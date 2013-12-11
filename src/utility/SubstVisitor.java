package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstracts.Literal;

import parser.AbstractFOLVisitor;
import sentence.AtomicSentence;
import sentence.QuantifiedSentence;
import sentence.Sentence;
import term.Function;
import term.Term;
import term.Variable;



// this class is one of the classed that define the behavior of visiting a FOLNode
// In this class the method subst is introduced to substitute a variables with a value
// this class extends the AbstractFOLVistor class which was just return 
// a copy of of the visited FOLNode
// Here because we are interested in the variable substitutions
// the methods visitVariables and visitQuantifiedSentence are overrriden to 
// direct them to use the newly introduces subst methods on their variables
public class SubstVisitor extends AbstractFOLVisitor {

	public SubstVisitor() {
	}

	
//	  Note: Refer to Artificial Intelligence A Modern Approach (3rd Edition):
//	  page 323.
//	  
//	  @param theta
//	             a substitution.
//	  @param sentence
//	             the substitution has been applied to.
//	  @return a new Sentence representing the result of applying the
//	          substitution theta to aSentence.
	  
	 
	// in case that we have a generic sentence
	public Sentence subst(Map<Variable, Term> theta, Sentence sentence) {
		// just call its accept function to see how it will behave with the given inputs
		// and return the change occurred based on the substitution
		return (Sentence) sentence.accept(this, theta);
	}
	// in case that we have a generic a term
	public Term subst(Map<Variable, Term> theta, Term aTerm) {
		// just call its accept function to see how it will behave with the given inputs
		// and return the change occurred based on the substitution
		return (Term) aTerm.accept(this, theta);
	}

	// in case that we have a function
	public Function subst(Map<Variable, Term> theta, Function function) {
		// just call its accept function to see how it will behave with the given inputs
		// and return the change occurred based on the substitution
		return (Function) function.accept(this, theta);
	}

	// in case that we have a Literal
	public Literal subst(Map<Variable, Term> theta, Literal literal) {
		// transform the literal to an atomic sentence first then
		// call its accept function to see how it will behave with the given inputs
		// and return the change occurred based on the substitution
		return literal.newInstance((AtomicSentence) literal
				.getAtomicSentence().accept(this, theta));
	}

	
	// here is where the real substitution is happening
	@SuppressWarnings("unchecked")
	@Override
	// in case of calling visit on a variable a substitution of the variable is returned
	public Object visitVariable(Variable variable, Object arg) {
		// we cast the input to Map<Variable, Term> 
		Map<Variable, Term> substitution = (Map<Variable, Term>) arg;
		// check if the variable exists inside the map if yes then return a copy of the term corresponding to it
		if (substitution.containsKey(variable)) {
			return substitution.get(variable).copy();
		}
		//else just return a copy of the input variable
		return variable.copy();
	}

	@SuppressWarnings("unchecked")
	@Override
	// in case of calling visit on a quantified sentence a substitution of it is returned.
	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg) {
		// we cast the input to Map<Variable, Term> 
		Map<Variable, Term> substitution = (Map<Variable, Term>) arg;
		// get the quantified sentence
		Sentence quantified = sentence.getQuantified();
		// call on it the the accept method so that the substitution of the sentence is done
		Sentence quantifiedAfterSubs = (Sentence) quantified.accept(this, arg);
		// get the list of the variables
		List<Variable> variables = new ArrayList<Variable>();
		// on each variable call the accept method to get substituted
		for (Variable v : sentence.getVariables()) {
			// check the variables from 
			//the substitution and the variables from theQuantified sentence
			Term st = substitution.get(v);
			if (null != st) {
				if (st instanceof Variable) {
					// Only if it is a variable to I replace it, otherwise
					// I drop it.
					variables.add((Variable) st.copy());
				}
			} else {
				// No substitution for the quantified variable, so
				// keep it.
				variables.add(v.copy());
			}
		}

		// If not variables remaining on the quantifier, then drop it
		if (variables.size() == 0) {
			return quantifiedAfterSubs;
		}
		// return the new Quantified sentence with variable substituted
		return new QuantifiedSentence(sentence.getQuantifier(), variables,
				quantifiedAfterSubs);
	}
}