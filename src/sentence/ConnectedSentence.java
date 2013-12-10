package sentence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.FOLVisitor;


// this class is representing a connected sentence
public class ConnectedSentence implements Sentence {
	// the value of the connector
	private String connector;
	// the value first and the secont part of the connected sentence
	private Sentence first, second;
	// the list of arguments which holds the 2 parts of the sentence
	private List<Sentence> args = new ArrayList<Sentence>();
	// the representation of the sentence human readable
	private String stringRep = null;
	// a hashcode to uniquely identify a sentence
	private int hashCode = 0;

	// Constructor to create a connected sentence taking the first and the second sentence and a connector
	public ConnectedSentence(String connector, Sentence first, Sentence second) {
		// setting the connector
		this.connector = connector;
		// the first part of the connected sentence
		this.first = first;
		// the second part of the connected sentence
		this.second = second;
		// add them to the args list
		args.add(first);
		args.add(second);
	}

	// getting the connector
	public String getConnector() {
		return connector;
	}
	// getting the first part of the sentence
	public Sentence getFirst() {
		return first;
	}
	// getting the seconf part of the sentence
	public Sentence getSecond() {
		return second;
	}

	//
	// START-Sentence - forced by the FOLNode Interface
	// a method used to return the symbolic name, forced by the interface of FOLNode
	public String getSymbolicName() {
		return getConnector();
	}
	// a connected sentence is considered to be a compound FOLNode
	public boolean isCompound() {
		return true;
	}
	// return the list of args which are the 2 sentence
	public List<Sentence> getArgs() {
		return Collections.unmodifiableList(args);
	}
	// define the behavior of the connected sentence when visited
	public Object accept(FOLVisitor v, Object arg) {
		return v.visitConnectedSentence(this, arg);
	}
	// a copy function
	public ConnectedSentence copy() {
		// just return a copy of the connected sentence
		return new ConnectedSentence(connector, first.copy(), second.copy());
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
		ConnectedSentence cs = (ConnectedSentence) o;
		// compare the connected sentence based on the connector and the 2 parts of the sentence
		return cs.getConnector().equals(getConnector())
				&& cs.getFirst().equals(getFirst())
				&& cs.getSecond().equals(getSecond());
	}
	
	// a hash code function that guarantee the uniqueness of the hash code 
	@Override
	public int hashCode() {
		// the calculation is based on the connector haschCode, the first part and the second part
		if (0 == hashCode) {
			hashCode = 17;
			hashCode = 37 * hashCode + getConnector().hashCode();
			hashCode = 37 * hashCode + getFirst().hashCode();
			hashCode = 37 * hashCode + getSecond().hashCode();
		}
		return hashCode;
	}

	// a method to string to show the connected sentence in a readable way
	// by appending "(" + first part +")" + connector + "("+ second part + ")"
	@Override
	public String toString() {
		if (null == stringRep) {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(first.toString());
			sb.append(" ");
			sb.append(connector);
			sb.append(" ");
			sb.append(second.toString());
			sb.append(")");
			stringRep = sb.toString();
		}
		return stringRep;
	}
}
