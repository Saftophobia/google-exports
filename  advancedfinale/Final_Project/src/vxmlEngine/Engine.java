package vxmlEngine;

import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import util.AudioPlayer;
import util.StateVariables;
import vxmlModel.Vxml;
import vxmlModel.VxmlParser;

public class Engine {
	StateVariables stateVariables;
	 VxmlParser vxml;
	 
	public Engine(VxmlParser vxmlClass)
	{
		this.vxml = vxmlClass;
		 this.stateVariables= new StateVariables();
	}
	
	public void eval()
	{
		this.vxml.getVxml().eval(stateVariables);
	}
}
