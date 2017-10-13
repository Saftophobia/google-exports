package term;

import java.util.List;

import utility.FOLVisitor;



// This class is used to represent a constant
public class Constant implements Term {
	// it has a value for the constant
	private String value;
	// a hashcode to uniquely identify the this object
	private int hashCode = 0;

	// a constructor that takes the constant value
	public Constant(String s) {
		// set the string to the value
		value = s;
	}
	// get the constant value
	public String getValue() {
		return value;
	}

	//
	// START-Term
	// this method is forced by the FOLNode to return a symbolic value for the constant
	// in this case it is just the constant value
	public String getSymbolicName() {
		return getValue();
	}

	// this method is forced by the FOLNode to return a boolean value that indicate 
	// that a constant is not compound
	public boolean isCompound() {
		return false;
	}
	
	// Is not Compound, therefore should
	// return null for its arguments
	public List<Term> getArgs() {
		return null;
	}

	
	// The accept method that call the given visitor to determine its behavior
	// depending on the problem
	public Object accept(FOLVisitor v, Object arg) {
		// calling the visitor
		return v.visitConstant(this, arg);
	}

	// this method forced by the interface FOLNode to copy a constant
	public Constant copy() {
		return new Constant(value);
	}

	// END-Term
	//

	// this method is used to compare two constant
	@Override
	public boolean equals(Object o) {

		// same instance
		if (this == o) {
			return true;
		}
		// not he same class
		if (!(o instanceof Constant)) {
			return false;
		}
		// different objects
		Constant c = (Constant) o;
		// compare based on the constant value
		return c.getValue().equals(getValue());

	}

	// a hash function used to create a unique value to be used in hashmap - hashtable - hashset
	// it take into consideration the value hashcode to garantee the uniqueness
	@Override
	public int hashCode() {
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + value.hashCode();
		}
		return hashCode;
	}

	// a toString method to show the value of constant
	@Override
	public String toString() {
		return value;
	}
}
