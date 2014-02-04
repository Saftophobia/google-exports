package vxmlModel;

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

	ArrayList<Tag> children;

	Prompt currentPrompt;
	int countNoMatch;
	int maxNoMatch;
	int countNoInput;
	boolean noMatchExists = false;
	Thread timer;

	Grammar currentGrammar;
	private String fieldValue;

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
	public Object eval(final Object o) {
		// TODO Auto-generated method stub
		if (condition != null) {
			if (condition.contains("==")) {
				String firstOP = condition.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = condition.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (((StateVariables) o).VariableHashMap.get(firstOP) != secondOP) { // not
					// equal
					return null;
				}
			} else {
				if (condition.contains("!=")) {
					String firstOP = condition.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = condition.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (((StateVariables) o).VariableHashMap.get(firstOP) == secondOP) { // not
						// equal
						return null;
					}
				}
			}
		}

		if (expr != null && name != null) {
			if (!expr.equalsIgnoreCase("undefined")) {
				((StateVariables) o).VariableHashMap.put(name, expr);
				return null;
			}
		}

		// done with attributes

		for (Tag tag : children) {
			if (tag instanceof Grammar) {
				currentGrammar = (Grammar) tag;
			}
		}

		for (Tag tag : children) {
			if (tag instanceof NoMatch) {
				noMatchExists = true;
				maxNoMatch++;
			}
		}

		// Got Grammar
		for (Tag tag : children) {
			if (tag instanceof Prompt) {
				boolean match = false;
				do {
					tag.eval(o);
					if (timer != null) {
						timer.stop();
					}
					timer = new Thread() {
						public void run() {
							while (fieldValue == null) {
								try {
									sleep(5000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (fieldValue == null) {
									noInputCase(o);
								}
								System.out.println("here");
							}
						}
					};
					timer.start();
					fieldValue = ((StateVariables) o).inputSim.OpenMic();
					match = (boolean) currentGrammar.eval(fieldValue);
					if (!match) {
						fieldValue = null;
						countNoMatch++;
						noMatchCase(o);
					}
					if(!noMatchExists){
						fieldValue = "";
					}
					System.out.println(match + " " + noMatchExists);
				} while (!match && noMatchExists);
			}
		}

		return null;
	}

	public void noInputCase(Object o) {
		NoInput noInput = null;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof NoInput) {
				noInput = (vxmlModel.NoInput) children.get(i);
				noInput.eval(o);
			}

		}

	}

	public void noMatchCase(Object o) {
		NoMatch noMatch = null;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof NoMatch) {
				noMatch = (vxmlModel.NoMatch) children.get(i);
				Object[] input = new Object[] { o,
						new Integer(Math.min(countNoMatch, (maxNoMatch))) };
				noMatch.eval(input);

			}
		}
	}

	public void filledCase(Object o) {
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
