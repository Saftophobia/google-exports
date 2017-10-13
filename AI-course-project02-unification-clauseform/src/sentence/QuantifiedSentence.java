package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.FOLNode;

import term.Variable;
import utility.FOLVisitor;

//this class is representing a Quantified sentence
public class QuantifiedSentence implements Sentence {
	// Quantifier value
	private String quantifier;
	// a list of the variables after the quantifier
	private List<Variable> variables = new ArrayList<Variable>();
	// the quantified sentence
	private Sentence quantified;
	// a list of args variables + quantified sentence
	private List<FOLNode> args = new ArrayList<FOLNode>();
	// a human readable representation
	private String stringRep = null;
	// a unique value for hashcode
	private int hashCode = 0;

	// Constructor to create a connected sentence taking a quantifier ,
	// variables and a quatified sentence
	public QuantifiedSentence(String quantifier, List<Variable> variables,
			Sentence quantified) {
		// set the quantifier
		this.quantifier = quantifier;
		// set the variables
		this.variables.addAll(variables);
		// set the quantified sentence
		this.quantified = quantified;
		// add the variables and the sentence to the agrs
		this.args.addAll(variables);
		this.args.add(quantified);
	}

	// get the quantifier
	public String getQuantifier() {
		return quantifier;
	}

	// get the variables
	public List<Variable> getVariables() {
		return Collections.unmodifiableList(variables);
	}

	// get the quantified sentence
	public Sentence getQuantified() {
		return quantified;
	}

	//
	// START-Sentence
	// a method used to return the symbolic name, forced by the interface of
	// FOLNode
	public String getSymbolicName() {
		return getQuantifier();
	}

	// a quantified snetence is considered to be a compound FOLNode
	public boolean isCompound() {
		return true;
	}

	// return the list of variables and quatified sentences
	public List<FOLNode> getArgs() {
		return Collections.unmodifiableList(args);
	}

	// define the behavior of the quantified sentence when visited
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitQuantifiedSentence(this, arg);
	}

	// acopy function
	public QuantifiedSentence copy() {
		// copying the variables one by one
		List<Variable> copyVars = new ArrayList<Variable>();
		for (Variable v : variables) {
			copyVars.add(v.copy());
		}
		// returning a new quantified sentence with same quantifier and
		// variables copy and sentence copy
		return new QuantifiedSentence(quantifier, copyVars, quantified.copy());
	}

	// END-Sentence
	//

	// a method to compare 2 quantified sentence
	@Override
	public boolean equals(Object o) {
		// same instance
		if (this == o) {
			return true;
		}
		// Different classes
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		// Different instances
		QuantifiedSentence cs = (QuantifiedSentence) o;
		// compare the connected sentence based on quantifier, variables and sentence
		return cs.quantifier.equals(quantifier)
				&& cs.variables.equals(variables)
				&& cs.quantified.equals(quantified);
	}
	// a hash code function that guarantee the uniqueness of the hash code
	@Override
	public int hashCode() {
		// the calculation is based on the quantifier and the variables and the quantified sentence
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + quantifier.hashCode();
			for (Variable v : variables) {
				hashCode = 37 * hashCode + v.hashCode();
			}
			hashCode = hashCode * 37 + quantified.hashCode();
		}
		return hashCode;
	}

	
	// a method to string to show the connected sentence in a readable way
	// by appending "Quantifier" + variables, variables, ... + sentence
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append(quantifier);
			sb.append(" ");
			for (Variable v : variables) {
				sb.append(v.toString());
				sb.append(" ");
			}
			sb.append(quantified.toString());
			stringRep = sb.toString();
		}
		return stringRep;
	}
}