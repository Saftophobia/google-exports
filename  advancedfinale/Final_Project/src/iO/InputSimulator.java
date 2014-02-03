package iO;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import util.SphinxListener;

public class InputSimulator {
	Scanner s = new Scanner(new InputStreamReader(System.in));
	ArrayList<SphinxListener> listeners = new ArrayList<SphinxListener>();
	Semaphore wait = new Semaphore(0);

	public InputSimulator() {

	}

	public void AddListener(SphinxListener s) {
		listeners.add(s);
	}

	public Output OpenMic() {
		s = new Scanner(new InputStreamReader(System.in));
		final Output o = new Output();
		o.value = s.nextLine();
		o.timeout = false;

		return o;
	}

	public class Output {
		public boolean timeout;
		public String value;
	}

}
