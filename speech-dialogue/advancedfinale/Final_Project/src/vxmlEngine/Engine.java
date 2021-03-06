package vxmlEngine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import util.AudioPlayer;
import util.FreeTTSListener;
import util.HelpListener;
import util.StateVariables;
import vxmlModel.Vxml;
import vxmlModel.VxmlParser;

public class Engine implements HelpListener{
	StateVariables stateVariables;
	VxmlParser vxml;

	public Engine(VxmlParser vxmlClass) {
		this.vxml = vxmlClass;
		this.stateVariables = new StateVariables();
		this.stateVariables.root = vxml.getVxml();
		stateVariables.inputSim.addHelpListeners(this);
	}

	public void AddfreeTTsListener(FreeTTSListener s) {
		stateVariables.Listerners.add(s);
	}
	
	

	public void eval() {
		
		this.vxml.getVxml().eval(stateVariables);
	}

	@Override
	public void HelpMe(Object o) {
		// TODO Auto-generated method stub
		stateVariables.currentHelpTag.HelpMe(stateVariables);
		
	}
}
