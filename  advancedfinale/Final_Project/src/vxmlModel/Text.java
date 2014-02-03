package vxmlModel;

import util.FreeTTSListener;
import util.StateVariables;

public class Text extends DataHolder {

	String valueText;

	public Text(String valueText) {
		this.valueText = valueText;
	}

	@Override
	public Object eval(StateVariables o) {
		for (FreeTTSListener listener : o.Listerners) {
			listener.Say(this.valueText);
		}
		return null;
	}

}
