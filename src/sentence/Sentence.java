package sentence;

import parser.FOLNode;


// this an interface to represent a generic sentence
public interface Sentence extends FOLNode {
	// it only forces the copy method
	Sentence copy();
}
