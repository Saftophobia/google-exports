package vxmlModel;

import java.util.ArrayList;

import util.StateVariables;
import util.StaticMethods;

public class Goto extends DataHolder {

	String expr;
	String exprItem;
	String fetchAudio;
	String fetchHint;
	String fetchTimeOut;
	String maxAge;
	String maxStale;
	String next;
	String nextItem;

	public Goto(String expr, String exprItem, String fetchAudio,
			String fetchHint, String fetchTimeOut, String maxAge,
			String maxStale, String next, String nextItem) {
		super();
		this.expr = expr;
		this.exprItem = exprItem;
		this.fetchAudio = fetchAudio;
		this.fetchHint = fetchHint;
		this.fetchTimeOut = fetchTimeOut;
		this.maxAge = maxAge;
		this.maxStale = maxStale;
		this.next = next;
		this.nextItem = nextItem;
	}

	public String getExpr() {
		return expr;
	}

	public String getExprItem() {
		return exprItem;
	}

	public String getFetchAudio() {
		return fetchAudio;
	}

	public String getFetchHint() {
		return fetchHint;
	}

	public String getFetchTimeOut() {
		return fetchTimeOut;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public String getMaxStale() {
		return maxStale;
	}

	public String getNext() {
		return next;
	}

	public String getNextItem() {
		return nextItem;
	}

	@Override
	public Object eval(Object o) {
		if (fetchAudio != null) {
			((StateVariables) o).audioPlayer.playSound(fetchAudio, 0);
		}
		if (expr != null) {
			Tag branch = searchDFS(
					((StateVariables) o).VariableHashMap.get(exprItem),
					((StateVariables) o).LastForm);
			if (branch != null) {
				branch.eval(o);
			}
		}
		if (exprItem != null) {
			System.out.println(((StateVariables)o).VariableHashMap.get("GotoVar"));
			
			Tag branch = searchDFS(
					((StateVariables) o).VariableHashMap.get(exprItem),
					((StateVariables) o).LastForm);
			if (branch != null) {
				branch.eval(o);
			}
		}
		if (next != null) {
			if (next.startsWith("#")) {
				Tag branch = searchDFS(next.replace("#", ""),
						((StateVariables) o).root);
				if (branch != null) {
					branch.eval(o);
				}
			} else {
				System.out.println("Maybe in another life !!!");
			}

		}
		if (nextItem != null) {
			Tag branch = searchDFS(nextItem, ((StateVariables) o).LastForm);
			if (branch != null) {
				branch.eval(o);
			}
		}
		return null;
	}

	public Tag SearchHelper1(TagHolder root, String searchFor) {
		ArrayList<Tag> children = root.getChildren();
		for (Tag tag : children) {
			if (tag instanceof Field) {
				if (((Field) tag).name.equals(StaticMethods.Normalize(searchFor))) {
					return tag;
				}
			}
			if (tag instanceof Block) {
				if (((Block) tag).name.equals(StaticMethods.Normalize(searchFor))) {
					return tag;
				}
			}
			if (tag instanceof TagHolder) {
				Tag t = SearchHelper1((TagHolder) tag, searchFor);
				if(t != null)
				{
					return t;
				}
			}
		}
		return null;
	}

	public Tag SearchHelper2(TagHolder root, String searchFor) {
		ArrayList<Tag> children = root.getChildren();

		for (Tag tag : children) {
			if (tag instanceof Form) {
				if (((Form) tag).id.equals(StaticMethods.Normalize(searchFor))) {
					return tag;
				}
			}
			if (tag instanceof TagHolder) {
				Tag t= SearchHelper2((TagHolder) tag, searchFor);
				if(t != null)
				{
					return t;
				}
			}
		}
		return null;
	}

	public Tag searchDFS(String searchFor, Tag inWhat) {
		
		if (inWhat instanceof Vxml) {
			return SearchHelper2((TagHolder) inWhat, searchFor);
		} else {
			return SearchHelper1((TagHolder) inWhat, searchFor);
		}
	}

}
