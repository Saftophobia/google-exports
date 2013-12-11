package main;

import java.util.ArrayList;
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
	FOLDomain domain;
	ArrayList<String> unifierSamples;
	ArrayList<String> ClauseSamples;
	Unifier unifier;
	FOLParser parser;
	CNFConverter cnfConv;

	public Main() {
		setupDomain();
		setupSamples();
		// Creating an instance of the parser Class giving it the domain
		// that we have created above
		parser = new FOLParser(domain);

	}

	public void setupSamples() {
		unifierSamples = new ArrayList<String>();
		ClauseSamples = new ArrayList<String>();
		// The Sample:
		// a) P(x,g(x),g(f(a)))and P(f(u),v,v)
		// b) P(a,y,f(y)) and P(z,z,u)
		// c) f(x,g(x),x)and f(g(u),g(g(z)),z)

		unifierSamples.add("P(x, g(x), g(f(a)))");
		unifierSamples.add("P(f(u),v,v)");
		unifierSamples.add("P(a, y ,f(y))");
		unifierSamples.add("P(z, z, u)");
		unifierSamples.add("P(f(x, g(x), x))");
		unifierSamples.add("P(f(g(u), g(g(z)), z))");
		// as written in the project description

		ClauseSamples
				.add("FORALL x (P(x) <=> (Q(x) AND EXISTS y (Q(y) AND R(y,x) ) ) )");

		ClauseSamples.add("EXISTS x (P(x) AND FORALL x (Q(x) => (NOT P(x))))");

	}

	public void setupDomain() {
		// Creating a domain instance for the sample
		// using this class the Symbols are defined so that the parser know
		// how to parse and create the objects
		// So we introduced the constant "a", the function "f and g" and the
		// predicate "P" for example
		// NOTE: you can apply any domain modifications
		domain = new FOLDomain();
		domain.addConstant("a");
		domain.addConstant("b");
		domain.addConstant("c");
		domain.addConstant("d");
		domain.addConstant("e");

		domain.addFunction("f");
		domain.addFunction("g");
		domain.addFunction("h");

		domain.addPredicate("P");
		domain.addPredicate("R");
		domain.addPredicate("Q");

	}

	// Helper method to parse and call the unification methods
	public void Unify(boolean trace) {
		// Creating a new instance of the unifier class
		// using this class the unification is done
		unifier = new Unifier(trace);
		for (int i = 0; i < unifierSamples.size() - 1; i++) {
			// Inputs to be unified
			String st1 = unifierSamples.get(i);
			String st2 = unifierSamples.get(i + 1);

			// check whether the unified values are terms or sentences
			if (Character.isLowerCase(st1.charAt(0))
					&& Character.isLowerCase(st2.charAt(0))) {
				// using the parser to parse the examples of the sample
				// t1 represents the 1st term
				Term t1 = parser.parseTerm(st1);
				// t2 represents the 2nd term
				Term t2 = parser.parseTerm(st2);
				// show unification results if exists
				System.out.println(unifier.unify(t1, t2));
			} else { // s1 represents the 1st sentence
				Sentence s1 = parser.parse(st1);
				// s2 represents the 2nd sentence
				Sentence s2 = parser.parse(st2);
				// show unification results if exists
				System.out.println(unifier.unify(s1, s2));
			}
		}
	}

	public void ClauseForm(boolean trace) {
		// creating a new CNF converter giving it a parser
		cnfConv = new CNFConverter(parser);

		for (int i = 0; i < ClauseSamples.size(); i++) {
			CNF cnf = cnfConv.convertToCNF(parser.parse(ClauseSamples.get(i)),trace);
			//System.out.println(cnf.toString());

			String[] flattened = cnf.toString().split("\\[");

			for (int j = 0; j < flattened.length; j++) {
				if (j == 0) {
					System.out.println("ClauseForm:\t" + flattened[0]);
				} else {
					System.out.println("\t[ " + flattened[j]);
				}

			}

		}
		;

	}

	// The main method that run the two methods (Unify and ClauseForm)
	public static void main(String[] args) {
		Main m = new Main();
		 m.Unify(true);
		 System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
		m.ClauseForm(true);

	}

}
