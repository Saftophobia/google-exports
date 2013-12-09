package sentence;

import java.util.List;

import term.Term;

public interface AtomicSentence extends Sentence {
	List<Term> getArgs();

	AtomicSentence copy();
}
