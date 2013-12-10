package main;

import java.util.Map;

import parser.FOLDomain;
import parser.FOLParser;
import sentence.Sentence;
import term.Term;
import term.Variable;
import unifier.Unifier;
import abstracts.CNF;
import clause.CNFConverter;

public class Main {

	// The main method that run the two methods (Unify and XXXX)
	public static void main(String[] args) {

		// The Sample:
		// a) P(x,g(x),g(f(a)))and P(f(u),v,v)
		// b) P(a,y,f(y)) and P(z,z,u)
		// c) f(x,g(x),x)and f(g(u),g(g(z)),z)

		// Creating a new instance of the unifier class
		// using this class the unification is done
		Unifier unifier = new Unifier();

		// Creating a domain instance for the sample
		// using this class the Symbols are defined so that the parser know
		// how to parse and create the objects
		// So we introduced the constant "a", the function "f and g" and the
		// predicate "P"
		FOLDomain domain = new FOLDomain();
		domain.addConstant("a");
		domain.addFunction("f");
		domain.addFunction("g");
		domain.addPredicate("P");

		// Creating an instance of the parser Class giving it the domain
		// that we have created above
		FOLParser parser = new FOLParser(domain);

		// using the parser to parse the first example of the sample
		// s1 holds the parsed version of (P(x,g(x),g(f(a))))
		Sentence s1 = parser.parse("P(x, g(x), g(f(a)))");
		// s1 holds the parsed version of (P(f(u),v,v))
		Sentence s2 = parser.parse("P(f(u),v,v)");
		// giving the two sentences to the unify method in the unifier instance
		// then getting the unification in the results variable. variable and
		// its unification
		Map<Variable, Term> result = unifier.unify(s1, s2);
		// printing the results.
		System.out.println(result);

		// using the parser to parse the second example of the sample
		// s1 holds the parsed version of (P(a, y ,f(y)))
		s1 = parser.parse("P(a, y ,f(y))");
		// s2 holds the parsed version of (P(z, z, u))
		s2 = parser.parse("P(z, z, u)");
		// giving the two sentences to the unify method in the unifier instance
		// then getting the unification in the results variable. variable and
		// its unification
		result = unifier.unify(s1, s2);
		// printing the results.
		System.out.println(result);

		// using the parser to parse the second example of the sample
		// s1 holds the parsed version of (f(x, g(x), x))
		Term t1 = parser.parseTerm("f(x, g(x), x)");
		// s2 holds the parsed version of (f(g(u), g(g(z)), z))
		Term t2 = parser.parseTerm("f(g(u), g(g(z)), z)");
		result = unifier.unify(t1, t2);
		// printing the results.
		System.out.println(result);

		// CNF converter
		// creating a new domain
		domain = new FOLDomain();
		// adding predicate P
		domain.addPredicate("R");
		// adding predicate R
		domain.addPredicate("Q");
		// adding predicate P
		domain.addPredicate("P");
		// crating a new parser with the new domain
		parser = new FOLParser(domain);
		// parsing the sentence
		// "FORALL x (P(x) <=> (Q(x) AND EXISTS y (Q(y) AND R(y,x) ) ) )"
		// and adding the result to s1
		s1 = parser
				.parse("FORALL x (P(x) <=> (Q(x) AND EXISTS y (Q(y) AND R(y,x) ) ) )");
		// creating a new CNF converter giving it a parser
		CNFConverter cnfConv = new CNFConverter(parser);
		// calling the convert method with s1 and holding the result in object CNF
		CNF cnf = cnfConv.convertToCNF(s1);
		// printing the results
		System.out.println(cnf.toString());
	}

}
