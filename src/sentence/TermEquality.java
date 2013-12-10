package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.FOLVisitor;
import term.Term;

// a class to represent the equality sentence
public class TermEquality implements AtomicSentence {
	// Term1 and Term2 are the two parts of the equality
	private Term term1, term2;
	// list of terms
	private List<Term> terms = new ArrayList<Term>();
	// a human representation
	private String stringRep = null;
	// a hashcode value for uniquely identify the results
	private int hashCode = 0;

	// return the symbol "="
	public static String getEqualitySynbol() {
		return "=";
	}

	// constructor taking the 2 terms of equality
	public TermEquality(Term term1, Term term2) {
		// set term1
		this.term1 = term1;
		// set term2
		this.term2 = term2;
		// add term1 and term2 to the terms
		terms.add(term1);
		terms.add(term2);
	}

	//get term1
	public Term getTerm1() {
		return term1;
	}

	//get term2
	public Term getTerm2() {
		return term2;
	}

	//
	// START-AtomicSentence
	//a method used to return the symbolic name, forced by the interface of
	// FOLNode
	public String getSymbolicName() {
		return getEqualitySynbol();
	}
	// a Equality sentence is compound
	public boolean isCompound() {
		return true;
	}
	// get the list of terms
	public List<Term> getArgs() {
		return Collections.unmodifiableList(terms);
	}
	// define the behavior of the equality sentence when visited
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitTermEquality(this, arg);
	}

	// a copy function
	public TermEquality copy() {
		//return a new copy of the equality sentece
		return new TermEquality(term1.copy(), term2.copy());
	}

	// END-AtomicSentence
	//
	
	// a method to compare 2 equality sentences
	@Override
	public boolean equals(Object o) {
		// same instance
		if (this == o) {
			return true;
		}
		// different classes
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		// different instances
		TermEquality te = (TermEquality) o;
		// compare two equality sentences based on the terms
		return te.getTerm1().equals(term1) && te.getTerm2().equals(term2);
	}

	// a hash code function that guarantee the uniqueness of the hash code
	@Override
	public int hashCode() {
		// the calculation is based on the the hashcode values of the two terms
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + getTerm1().hashCode();
			hashCode = 37 * hashCode + getTerm2().hashCode();
		}
		return hashCode;
	}

	// a two string method to show the equality sentence
	// it appends term1 + "=" + term2
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append(term1.toString());
			sb.append(" = ");
			sb.append(term2.toString());
			stringRep = sb.toString();
		}
		return stringRep;
	}
}