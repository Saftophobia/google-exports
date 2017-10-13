package clause;

import java.util.Map;

import sentence.Sentence;
import term.Term;
import term.Variable;



/**
 * Standardize Output.
 * 
 */
public class StandardizeApartResult {
	private Sentence originalSentence = null; //original sentence
	private Sentence standardized = null; //standardized sentence (output)
	private Map<Variable, Term> forwardSubstitution = null; // used for forward substitution 
	private Map<Variable, Term> reverseSubstitution = null; // used for backward substitution

	//constructor with setters
	public StandardizeApartResult(Sentence originalSentence,
			Sentence standardized, Map<Variable, Term> forwardSubstitution,
			Map<Variable, Term> reverseSubstitution) {
		this.originalSentence = originalSentence;
		this.standardized = standardized;
		this.forwardSubstitution = forwardSubstitution;
		this.reverseSubstitution = reverseSubstitution;
	}
	//original sentence getter
	public Sentence getOriginalSentence() {
		return originalSentence;
	}
	//standardized sentence getter
	public Sentence getStandardized() {
		return standardized;
	}
	//forwardsubst. getter
	public Map<Variable, Term> getForwardSubstitution() {
		return forwardSubstitution;
	}
	//backward subst. getter
	public Map<Variable, Term> getReverseSubstitution() {
		return reverseSubstitution;
	}
}
