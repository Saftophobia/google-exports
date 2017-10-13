package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utility.FOLVisitor;
import utility.SyncategorematicSymbols;

//this class is representing a negated sentence
public class NotSentence implements Sentence {
	// the negated sentence
	private Sentence negated;
	// list of the sentence args which is the negated sentence
	private List<Sentence> args = new ArrayList<Sentence>();
	// the representation of the sentence human readable
	private String stringRep = null;
	// a hashcode to uniquely identify a sentence
	private int hashCode = 0;

	// Constructor to create a connected sentence taking the negated sentence
	public NotSentence(Sentence negated) {
		// setting the sentence
		this.negated = negated;
		// add it to the args of the sentence
		args.add(negated);
	}

	// return the negated sentence
	public Sentence getNegated() {
		return negated;
	}

	//
	// START-Sentence
	// just return "NOT" as a symbol
	public String getSymbolicName() {
		return SyncategorematicSymbols.NOT;
	}

	// a negated sentence is considered to be a compound FOLNode
	public boolean isCompound() {
		return true;
	}

	// return the list of args which are the negated sentence
	public List<Sentence> getArgs() {
		return Collections.unmodifiableList(args);
	}

	// define the behavior of the connected sentence when visited
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitNotSentence(this, arg);
	}

	// a copy function
	public NotSentence copy() {
		// jsut return a copy of the negated sentence
		return new NotSentence(negated.copy());
	}

	// END-Sentence
	//

	// a method to compare 2 connected sentences
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
		NotSentence ns = (NotSentence) o;
		// compare the two negated sentences together
		return (ns.negated.equals(negated));
	}

	// a hash code function that guarantee the uniqueness of the hash code
	@Override
	public int hashCode() {
		// the calculation is based on the negated sentence

		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + negated.hashCode();
		}
		return hashCode;
	}

	// a method to string to show the connected sentence in a readable way
	// by appending "NOT("+  sentence + ")"
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append("NOT(");
			sb.append(negated.toString());
			sb.append(")");
			stringRep = sb.toString();
		}
		return stringRep;
	}
}
