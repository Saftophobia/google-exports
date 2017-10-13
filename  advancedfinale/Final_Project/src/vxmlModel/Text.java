package vxmlModel;

import util.FreeTTSListener;
import util.StateVariables;

public class Text extends DataHolder {

	String valueText;

	public Text(String valueText) {
		this.valueText = valueText;
	}

	@Override
	public Object eval(Object o) {
		for (FreeTTSListener listener : ((StateVariables)o).Listerners) {
			listener.Say(this.valueText);
		}
		return null;
	}

}
