package vxmlEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import util.AudioPlayer;
import util.FreeTTSListener;
import util.StateVariables;
import vxmlModel.Vxml;
import vxmlModel.VxmlParser;

public class Engine {
	StateVariables stateVariables;
	VxmlParser vxml;

	public Engine(VxmlParser vxmlClass) {
		this.vxml = vxmlClass;
		this.stateVariables = new StateVariables();
	}

	public void AddfreeTTsListener(FreeTTSListener s) {
		stateVariables.Listerners.add(s);
	}
	
	

	public void eval() {
		this.vxml.getVxml().eval(stateVariables);
	}
}
