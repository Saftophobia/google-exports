package iO;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import util.HelpListener;
import util.SphinxListener;
import util.StaticMethods;

public class InputSimulator {
	Scanner s = new Scanner(new InputStreamReader(System.in));
	ArrayList<SphinxListener> listeners = new ArrayList<SphinxListener>();
	ArrayList<HelpListener> helperListeners = new ArrayList<HelpListener>();

	public InputSimulator() {

	}

	public void addHelpListeners(HelpListener s) {
		helperListeners.add(s);
	}

	public void AddListener(SphinxListener s) {
		listeners.add(s);
	}

	public String OpenMic() {
//		System.out.println("MIC MIC MIC MIC MIC ");
		s = new Scanner(new InputStreamReader(System.in));

		String output = s.nextLine();
		if (StaticMethods.Normalize(output).equals("help")) {
			for (HelpListener hl : helperListeners) {
				hl.HelpMe(null);
			}
		}

		return output;
	}

	public String OpenKeyboard() {
		System.out.println("Keyboard Keyboard Keyboard Keyboard Keyboard");
		
		s = new Scanner(new InputStreamReader(System.in));

		String output = s.nextLine();
		if (StaticMethods.Normalize(output).equals("help")) {
			for (HelpListener hl : helperListeners) {
				hl.HelpMe(null);
			}
		}
		return output;
	}

}
