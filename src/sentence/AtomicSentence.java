package sentence;

import java.util.List;

import term.Term;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface AtomicSentence extends Sentence {
	List<Term> getArgs();

	AtomicSentence copy();
}
