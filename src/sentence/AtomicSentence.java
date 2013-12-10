package sentence;

import java.util.List;

import term.Term;


// this is an interface represent a more specific type of sentences which are atomic
// that consists only of one atom.
public interface AtomicSentence extends Sentence {
	
	// force the getting of its arguments
	List<Term> getArgs();

	// force the copying of atomic sentences
	AtomicSentence copy();
}
