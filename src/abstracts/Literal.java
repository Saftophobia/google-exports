package abstracts;

import sentence.AtomicSentence;
import term.Term;


public class Literal {
	private AtomicSentence atom = null; // the atomic sentence used to get the literal.
	private boolean negativeLiteral = false; //whether the literal is negated or not.
	private String strRep = null; //output string

	//constructor with atomic sentence setter.
	public Literal(AtomicSentence atom) {
		this.atom = atom;
	}
	//constructor with atomic sentence and sign setters.
	public Literal(AtomicSentence atom, boolean negated) {
		this.atom = atom;
		this.negativeLiteral = negated;
	}
	//create new literal with the given atomic sentence.
	public Literal newInstance(AtomicSentence atom) {
		return new Literal(atom, negativeLiteral);
	}
	//check whether literal is positive
	public boolean isPositiveLiteral() {
		return !negativeLiteral;
	}
	//check whether literal is negated
	public boolean isNegativeLiteral() {
		return negativeLiteral;
	}
	// literal's atomic sentence getter
	public AtomicSentence getAtomicSentence() {
		return atom; 
	}

	//print the literal, adds ~ if its negated.
	@Override
	public String toString() {
		if (null == strRep) {
			StringBuilder sb = new StringBuilder();
			if (isNegativeLiteral()) {
				sb.append("~");
			}
			sb.append(getAtomicSentence().toString());
			strRep = sb.toString();
		}

		return strRep;
	}

	//implemented comparator for literals,
	//compares classes, instanceof, each and every attribute in the two objects to be compared.
	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o.getClass() != getClass()) {
			// This prevents ReducedLiterals
			// being treated as equivalent to
			// normal Literals.
			return false;
		}
		if (!(o instanceof Literal)) {
			return false;
		}
		Literal l = (Literal) o;
		return l.isPositiveLiteral() == isPositiveLiteral()
				&& l.getAtomicSentence().getSymbolicName()
						.equals(atom.getSymbolicName())
				&& l.getAtomicSentence().getArgs().equals(atom.getArgs());
	}

	
}
