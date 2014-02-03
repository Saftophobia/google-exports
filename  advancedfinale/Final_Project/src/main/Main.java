package main;

import java.io.File;

import vxmlEngine.Engine;
import vxmlModel.VxmlParser;

public class Main {
	public static void main(String[] args) {

		VxmlParser parser = new VxmlParser(new File("src/test.vxml"));
		Engine e = new Engine(parser);
		e.eval();
		
	}
}
