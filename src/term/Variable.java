package term;

import java.util.List;

import parser.FOLVisitor;


// this class is used to represent a variable class
public class Variable implements Term {
	// variable value
	private String value;
	// a hashcode to uniquely identify a variable
	private int hashCode = 0;
	// a value to determine the index of a variable in a function 
	//(not used in the code used only in the hashCode function)
	private int indexical = -1;
	// a constructor with variable s
	public Variable(String s) {
		value = s.trim();
	}

	 // a constructor with value and index (not used in the code used only in the copying)
	public Variable(String s, int idx) {
		//set the string s to the value after removing white spaces
		value = s.trim();
		// set the index
		indexical = idx;
	}

	// return the value of the variable
	public String getValue() {
		return value;
	}

	//
	// START-Term
	// a method used to return the symbolic name forced by the interface of FOLNode
	public String getSymbolicName() {
		return getValue();
	}
	// a variable is not compound
	public boolean isCompound() {
		return false;
	}

	// Is not Compound, therefore should
	// return null for its arguments
	public List<Term> getArgs() {
		return null;
	}
	// this method forced by the FOLNode interface 
	//to define the behavior when ineracting with this variable
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitVariable(this, arg);
	}
	// copy a variable
	public Variable copy() {
		//returning a new instrance
		return new Variable(value, indexical);
	}

	// END-Term
	//
	
	// get the index value
	public int getIndexical() {
		return indexical;
	}

	// set the index value and reset the hashcode
	public void setIndexical(int idx) {
		indexical = idx;
		hashCode = 0;
	}

	// get the string value and the index concatenated together
	public String getIndexedValue() {
		return value + indexical;
	}

	// a method to compare two variable
	@Override
	public boolean equals(Object o) {
		// same instance
		if (this == o) {
			return true;
		}
		// different classes
		if (!(o instanceof Variable)) {
			return false;
		}
		// different variables instance
		Variable v = (Variable) o;
		// compare based on the index and the value 
		return v.getValue().equals(getValue())
				&& v.getIndexical() == getIndexical();
	}

	// hashcode function used to calculate a unique value
	// based on the value and the index
	@Override
	public int hashCode() {
		if (0 == hashCode) {
			hashCode = 17;
			hashCode += indexical;
			hashCode = 37 * hashCode + value.hashCode();
		}

		return hashCode;
	}

	// a to string function
	@Override
	public String toString() {
		return value;
	}

}