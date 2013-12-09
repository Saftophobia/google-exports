package main;

import java.util.Map;

import sentence.Sentence;

import parser.FOLDomain;
import parser.FOLParser;
import term.Term;
import term.Variable;
import unifier.Unifier;

public class Main {

	// The main method that run the two methods (Unify and XXXX)
	public static void main(String[] args) {
		
		// The Sample:
		//a) P(x,g(x),g(f(a)))and P(f(u),v,v) 
		//b) P(a,y,f(y)) and P(z,z,u)
		//c) f(x,g(x),x)and f(g(u),g(g(z)),z)
		
		// Creating a new instance of the unifier class 
		//using this class the unification is done
		Unifier unifier = new Unifier();
		
		// Creating a domain instance for the sample 
		// using this class the Symbols are defined so that the parser know 
		//how to parse and create the objects
		// So we introduced the constant "a", the function "f and g" and the predicate "P" 
		FOLDomain domain = new FOLDomain();
		domain.addConstant("a");
		domain.addFunction("f");
		domain.addFunction("g");
		domain.addPredicate("P");
	
		// Creating an instance of the parser Class giving it the domain
		//that we have created above
		FOLParser parser = new FOLParser(domain);
		
		// using the parser to parse the first example of the sample
		// s1 holds the parsed version of (P(x,g(x),g(f(a))))
		Sentence s1 = parser.parse("P(x, g(x), g(f(a)))");
		// s1 holds the parsed version of (P(f(u),v,v))
		Sentence s2 = parser.parse("P(f(u),v,v)");
		// giving the two sentences to the unify method in the unifier instance 
		// then getting the unification in the results variable. variable and its unification
		Map<Variable, Term> result = unifier.unify(s1, s2);
		// printing the results.
		System.out.println(result);
		
		// using the parser to parse the second example of the sample
		// s1 holds the parsed version of (P(a, y ,f(y)))
		s1 = parser.parse("P(a, y ,f(y))");
		// s2 holds the parsed version of (P(z, z, u))
		s2 = parser.parse("P(z, z, u)");
		// giving the two sentences to the unify method in the unifier instance 
		// then getting the unification in the results variable. variable and its unification
		result = unifier.unify(s1, s2);
		// printing the results.
		System.out.println(result);
		
		// using the parser to parse the second example of the sample
		// s1 holds the parsed version of (f(x, g(x), x))
		s1 = parser.parse("P(f(x, g(x), x))");
		// s2 holds the parsed version of (f(g(u), g(g(z)), z))
		s2 = parser.parse("P(f(g(u), g(g(z)), z))");
		result = unifier.unify(s1, s2);
		// printing the results.
		System.out.println(result);
	}
	
}
