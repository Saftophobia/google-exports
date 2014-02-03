package vxmlModel;

import iO.InputSimulator.Output;

import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;

public class Field extends TagHolder {

	String condition;
	String expr;
	String modal;
	String name;
	String type;
	String slot;

	int atWhichPrompt;
	int countNoMatch;
	int countNoInput;

	ArrayList<Tag> children;

	Prompt currentPrompt;

	public Field(String condition, String expr, String modal, String name,
			String type, String slot) {
		super();
		this.condition = condition;
		this.expr = expr;
		this.modal = modal;
		this.name = name;
		this.type = type;
		this.slot = slot;
		this.children = new ArrayList<Tag>();
		identifier = 2;
	}

	public void addChild(Tag child) {
		child.parent = this;
		children.add(child);
	}

	public Tag getChild() {
		return children.get(parsingIndex++);
	}

	public void updateParsingIndex(int i) {
		parsingIndex = i;
	}

	public String getCondition() {
		return condition;
	}

	public String getExpr() {
		return expr;
	}

	public String getModal() {
		return modal;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSlot() {
		return slot;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}

	public ArrayList<Tag> getTagsByType(int identifier) {
		ArrayList<Tag> output = new ArrayList<Tag>();
		for (Tag child : children) {
			if (child.identifier == identifier) {
				output.add(child);
			}
		}
		return output;
	}

	@Override
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		if (condition != null) {
			if (condition.contains("==")) {
				String firstOP = condition.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = condition.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (o.VariableHashMap.get(firstOP) != secondOP) { // not
																	// equal
					return null;
				}
			} else {
				if (condition.contains("!=")) {
					String firstOP = condition.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = condition.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (o.VariableHashMap.get(firstOP) == secondOP) { // not
																		// equal
						return null;
					}
				}
			}
		}

		if (expr != null && name != null) {
			if (!expr.equalsIgnoreCase("undefined")) {
				o.VariableHashMap.put(name, expr);
				return null;
			}
		}

		// done with attributes

		Grammar grammar = null;
		// getGrammar
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof Grammar) {
				grammar = (vxmlModel.Grammar) children.get(i);

			}
		}

		for (int i = atWhichPrompt; i < children.size(); i++) {
			if (children.get(i) instanceof Prompt) {
				atWhichPrompt = i;
				currentPrompt = (vxmlModel.Prompt) children.get(i);
				currentPrompt.eval(o);
				Output input = o.inputSim.OpenMic();
				if (!input.timeout) {
					boolean match = grammar.evalValue(input.value);
					System.out.println("MATCH IS " + match);
					if (!match) {
						noMatchCase(o);
					}
				} else {
					noInputCase(o);
				}

			}

			if (children.get(i) instanceof Value) {
				for (FreeTTSListener listener : o.Listerners) {
					listener.Say((String) children.get(i).eval(o));
				}
			}
			if (children.get(i) instanceof Text) {

				children.get(i).eval(o);
			}
		}

		filledCase(o);

		return null;
	}

	public void noInputCase(StateVariables o) {
		NoInput noInput = null;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof NoInput) {
				noInput = (vxmlModel.NoInput) children.get(i);
			}

		}
		if (noInput != null)
			noInput.eval(o);
	}

	public void noMatchCase(StateVariables o) {
		NoMatch noMatch = null;
		for (int i = countNoMatch; i < children.size(); i++) {
			if (children.get(i) instanceof NoMatch) {
				noMatch = (vxmlModel.NoMatch) children.get(i);
			}

			// neval we nshoof 3ayzeen ne3mel eih

			countNoMatch++;
		}
		if (noMatch != null)
			noMatch.eval(o);
	}

	public void filledCase(StateVariables o) {
		Filled filled = null;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof Filled) {
				filled = (vxmlModel.Filled) children.get(i);
			}

			// neval we nshoof 3ayzeen ne3mel eih
		}

		if (filled != null)
			filled.eval(o);
	}

}
