package main;

import java.util.Map;

import parser.FOLDomain;
import parser.FOLParser;
import sentence.Sentence;
import term.Term;
import term.Variable;
import unifier.Unifier;

public class Main {

	
	public static void main(String[] args) {

		// FOLParser parser = new FOLParser(DomainFactory.knowsDomain());
		
//		a) P(x,g(x),g(f(a)))and P(f(u),v,v) 
//		b) P(a,y,f(y)) and P(z,z,u)
//		c) f(x,g(x),x)and f(g(u),g(g(z)),z)
		
		Unifier unifier = new Unifier();

		FOLDomain domain = new FOLDomain();
		domain.addConstant("a");
		domain.addFunction("f");
		domain.addFunction("g");
		domain.addPredicate("P");
	

		FOLParser parser = new FOLParser(domain);
		
		Sentence s1 = parser.parse("P(x, g(x), g(f(a)))");
		Sentence s2 = parser.parse("P(f(u),v,v)");
		Map<Variable, Term> result = unifier.unify(s1, s2);
		
		System.out.println(result);
		
		s1 = parser.parse("P(a, y ,f(y))");
		s2 = parser.parse("P(z, z, u)");
		result = unifier.unify(s1, s2);

		System.out.println(result);
		
		s1 = parser.parse("P(f(x, g(x), x))");
		s2 = parser.parse("P(f(g(u), g(g(z)), z))");
		result = unifier.unify(s1, s2);

		System.out.println(result);
	}
	
}
