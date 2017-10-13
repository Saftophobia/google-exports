package term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utility.FOLVisitor;


// this class is used to represent a Function
public class Function implements Term {
	// a function name EX: f
	private String functionName;
	// list of terms of the functions EX: (x,y)
	private List<Term> terms = new ArrayList<Term>(); 
	// a string representation of the function  EX: f(x,y)
	private String stringRep = null;
	// a hash code for having a unique value to identify the object
	private int hashCode = 0;

	
	// a constructor tot create a function given a function name and its terms
	public Function(String functionName, List<Term> terms) {
		// set the function name
		this.functionName = functionName;
		// set the terms
		this.terms.addAll(terms);
	}

	// return the function name
	public String getFunctionName() {
		return functionName;
	}

	// called by the getArgs() forced by the FOLNode interface
	public List<Term> getTerms() {
		return Collections.unmodifiableList(terms);
	}

	//
	// START-Term
	// 
	public String getSymbolicName() {
		return getFunctionName();
	}

	public boolean isCompound() {
		return true;
	}
	
	// a forced function from the FOLNode interface to get the terms of a function 
	public List<Term> getArgs() {
		return getTerms();
	}

	// a forced function from the FOLNode interface to define the the behavior
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitFunction(this, arg);
	}

	// copy a function
	public Function copy() {
		// copy its terms one by one
		List<Term> copyTerms = new ArrayList<Term>();
		for (Term t : terms) {
			copyTerms.add(t.copy());
		}
		// return a new instance having the same values of the functions
		return new Function(functionName, copyTerms);
	}

	// END-Term
	//

	// a method to compare two functions
	@Override
	public boolean equals(Object o) {
		// same instance
		if (this == o) {
			return true;
		}
		// different classes
		if (!(o instanceof Function)) {
			return false;
		}

		// two different functions
		Function f = (Function) o;
		// compare the two function based on the function name and terms
		return f.getFunctionName().equals(getFunctionName())
				&& f.getTerms().equals(getTerms());
	}

	// a hash code function to return a unique value
	// the function take into consideration the function name  hashcode
	// and the hashcode of all its terms.
	@Override
	public int hashCode() {
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + functionName.hashCode();
			for (Term t : terms) {
				hashCode = 37 * hashCode + t.hashCode();
			}
		}
		return hashCode;
	}

	// a to String method used to show a function
	// on this form EX: f(x,y,z)
	// by aoppending the function name f + ( + the terms +) 
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append(functionName);
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