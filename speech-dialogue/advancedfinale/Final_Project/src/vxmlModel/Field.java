package vxmlModel;

import java.util.ArrayList;

import javax.swing.text.FieldView;

import util.FreeTTSListener;
import util.HelpListener;
import util.StateVariables;

public class Field extends TagHolder implements HelpListener {

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
	boolean alreadyVisited = false;

	Grammar currentGrammar;
	private String fieldValue;
	int maxPrompt;
	int PromptCounter =1;

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
		((StateVariables) o).currentHelpTag = this;
		alreadyVisited = true;
		if (condition != null) {
			if (condition.contains("==")) {
				String firstOP = condition.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = condition.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (((StateVariables) o).VariableHashMap.get(firstOP) == null) {
					return null;
				}
				if (!((StateVariables) o).VariableHashMap.get(firstOP).equals(
						secondOP)) { // not
					// equal
					return null;
				}

			} else {
				if (condition.contains("!=")) {
					String firstOP = condition.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = condition.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (((StateVariables) o).VariableHashMap.get(firstOP) == null) {
						return null;
					}
					if (((StateVariables) o).VariableHashMap.get(firstOP)
							.equals(secondOP)) { // not
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
		
		for (Tag tag : children) {
			if (tag instanceof Prompt) {
				maxPrompt++;
				if(maxPrompt>0){
					PromptCounter = 1;
				}
				if(maxPrompt>1){
					PromptCounter = 2;
				}
			}
		}

		// Got Grammar
		for (Tag tag : children) {
			if (tag instanceof Prompt) {
				boolean match = false;
				if(((Prompt)tag).count != null && Integer.parseInt(((Prompt)tag).count)>1){
					continue;
				}
				tag.eval(o);
				((StateVariables) o).LastPrompt = (Prompt) tag;
				
				do {
					if (timer != null) {
						timer.stop();
					}
					timer = new Thread() {
						public void run() {
							while (fieldValue == null) {
								try {
									sleep((Integer.parseInt(((StateVariables) o).LastPrompt.timeout))*1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (fieldValue == null) {
									noInputCase(o);
								}
								// system.out.println("here");
							}
						}
					};
					timer.start();
					if (currentGrammar != null && currentGrammar.mode != null
							&& currentGrammar.mode.equals("dtmf")) {
						fieldValue = ((StateVariables) o).inputSim
								.OpenKeyboard();
					} else {
						fieldValue = ((StateVariables) o).inputSim.OpenMic();
					}
					match = (boolean) currentGrammar.eval(fieldValue);
					if (!match) {
						if (fieldValue.equals("help")) {
						
							
						} else {
							countNoMatch++;
							noMatchCase(o);

							if (!noMatchExists) {
								fieldValue = "";
							}

						}
					}
					// system.out.println(match + " " + noMatchExists);
				} while (!match && noMatchExists);
				// valid input
				if (fieldValue != null && !fieldValue.equals("")) {
					((StateVariables) o).VariableHashMap.put(this.name,
							this.fieldValue);
				}
			}

			if (tag instanceof Value) {
				for (FreeTTSListener sListener : ((StateVariables) o).Listerners) {
					sListener.Say((String) tag.eval(o));
				}
			}
			if (tag instanceof Text) {
				Text t = (Text) tag;
				if (t.valueText.replace(" ", "").replace("\n", "").length() > 0) {
					tag.eval(o);
				}
				// tag.eval(o);
			}

			if (tag instanceof Filled) {
				Filled f = (Filled) tag;
				f.eval(o);
			}

		}
		return null;
	}

	public void noInputCase(Object o) {
		int counter = 0;
		for (Tag tag : children) {
			if (tag instanceof Prompt) {
				if(counter<PromptCounter){
					counter++;
					((StateVariables) o).LastPrompt = (Prompt) tag;
				}
			}
		}
		if(maxPrompt>0){
			PromptCounter++;
			PromptCounter = Math.min(PromptCounter, maxPrompt);
		}
		NoInput noInput = null;
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof NoInput) {
				noInput = (vxmlModel.NoInput) children.get(i);
				noInput.eval(o);
			}

		}
		

	}

	public void noMatchCase(Object o) {
		int counter = 0;
		for (Tag tag : children) {
			if (tag instanceof Prompt) {
				if(counter<PromptCounter){
					counter++;
					((StateVariables) o).LastPrompt = (Prompt) tag;
				}
			}
		}
		if(maxPrompt>0){
			PromptCounter++;
			PromptCounter = Math.min(PromptCounter, maxPrompt);
		}
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

	@Override
	public void HelpMe(Object o) {
		// TODO Auto-generated method stub
		for (Tag t : this.children) {
			if (t instanceof Help) {
				t.eval(o);
			}
		}
		new Reprompt().eval(o);

	}

}
