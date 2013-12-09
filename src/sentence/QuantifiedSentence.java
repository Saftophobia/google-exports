package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parser.FOLNode;
import term.Term;
import term.Variable;

public class QuantifiedSentence implements Sentence {
	private String quantifier;
	private List<Variable> variables = new ArrayList<Variable>();
	private Sentence quantified;
	private List<FOLNode> args = new ArrayList<FOLNode>();
	private String stringRep = null;
	private int hashCode = 0;

	public QuantifiedSentence(String quantifier, List<Variable> variables,
			Sentence quantified) {
		this.quantifier = quantifier;
		this.variables.addAll(variables);
		this.quantified = quantified;
		this.args.addAll(variables);
		this.args.add(quantified);
	}

	public String getQuantifier() {
		return quantifier;
	}

	public List<Variable> getVariables() {
		return Collections.unmodifiableList(variables);
	}

	public Sentence getQuantified() {
		return quantified;
	}

	//
	// START-Sentence
	public String getSymbolicName() {
		return getQuantifier();
	}

	public boolean isCompound() {
		return true;
	}

	public List<FOLNode> getArgs() {
		return Collections.unmodifiableList(args);
	}

	@SuppressWarnings("unchecked")
	public Object accept(Object arg) {
//		List<Variable> variables = new ArrayList<Variable>();
//		for (Variable var : this.getVariables()) {
//			variables.add((Variable) var.accept(arg));
//		}
//
//		return new QuantifiedSentence(this.getQuantifier(), variables,
//				(Sentence) this.getQuantified().accept(arg));
		Map<Variable, Term> substitution = (Map<Variable, Term>) arg;

		Sentence quantified = this.getQuantified();
		Sentence quantifiedAfterSubs = (Sentence) quantified.accept(arg);

		List<Variable> variables = new ArrayList<Variable>();
		for (Variable v : this.getVariables()) {
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

		return new QuantifiedSentence(this.getQuantifier(), variables,
				quantifiedAfterSubs);
		}

	public QuantifiedSentence copy() {
		List<Variable> copyVars = new ArrayList<Variable>();
		for (Variable v : variables) {
			copyVars.add(v.copy());
		}
		return new QuantifiedSentence(quantifier, copyVars, quantified.copy());
	}

	// END-Sentence
	//

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		QuantifiedSentence cs = (QuantifiedSentence) o;
		return cs.quantifier.equals(quantifier)
				&& cs.variables.equals(variables)
				&& cs.quantified.equals(quantified);
	}

	@Override
	public int hashCode() {
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