package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.FOLVisitor;

import term.Term;

//this class is representing a predicate
public class Predicate implements AtomicSentence {
	// the predicate name value
	private String predicateName;
	// list of terms
	private List<Term> terms = new ArrayList<Term>();
	// the human representation
	private String stringRep = null;
	// a hashcode to uniquely identify a sentence
	private int hashCode = 0;

	// Constructor to create a connected sentence taking the first and the
	// second sentence and a connector
	public Predicate(String predicateName, List<Term> terms) {
		// set predicate name
		this.predicateName = predicateName;
		// set terms list
		this.terms.addAll(terms);
	}

	// get predicate name
	public String getPredicateName() {
		return predicateName;
	}

	// get predicate terms
	public List<Term> getTerms() {
		return Collections.unmodifiableList(terms);
	}

	//
	// START-AtomicSentence
	// a method used to return the symbolic name, forced by the interface of
	// FOLNode
	public String getSymbolicName() {
		return getPredicateName();
	}

	// a predicate is considered to be a compound FOLNode
	public boolean isCompound() {
		return true;
	}

	// return the list of terms
	public List<Term> getArgs() {
		return getTerms();
	}

	// define the behavior of the predicate when visited
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitPredicate(this, arg);
	}

	// a copy function
	public Predicate copy() {
		// return a copy of the predicate by copying its name and terms
		List<Term> copyTerms = new ArrayList<Term>();
		for (Term t : terms) {
			copyTerms.add(t.copy());
		}
		// return a new copy
		return new Predicate(predicateName, copyTerms);
	}

	// END-AtomicSentence
	//

	// a method to compare 2 predicates
	@Override
	public boolean equals(Object o) {
		// same instance
		if (this == o) {
			return true;
		}
		// Different classes
		if (!(o instanceof Predicate)) {
			return false;
		}
		// Different instances
		Predicate p = (Predicate) o;
		// compare the predicate based on the predicate name and terms
		return p.getPredicateName().equals(getPredicateName())
				&& p.getTerms().equals(getTerms());
	}

	// a hash code function that guarantee the uniqueness of the hash code
	@Override
	public int hashCode() {
		// the calculation is based on the predicate name and the terms
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + predicateName.hashCode();
			for (Term t : terms) {
				hashCode = 37 * hashCode + t.hashCode();
			}
		}
		return hashCode;
	}

	// a method to string to show the connected sentence in a readable way
	// by appending "P_NAME" + "(" + TERMS + ")"
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append(predicateName);
			sb.append("(");

			boolean first = true;
			for (Term t : terms) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append(t.toString());
			}

			sb.append(")");
			stringRep = sb.toString();
		}

		return stringRep;
	}
}