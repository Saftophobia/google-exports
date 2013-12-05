package util;

import parser.FOLNode;

public interface Sentence extends FOLNode {
	Sentence copy();
}
