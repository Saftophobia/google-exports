package util;

import java.util.List;

public interface AtomicSentence extends Sentence {
	List<Term> getArgs();

	AtomicSentence copy();
}
