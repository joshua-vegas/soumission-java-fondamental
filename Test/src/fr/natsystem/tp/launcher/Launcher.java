package fr.natsystem.tp.launcher;

import fr.natsystem.tp.datamodel.TestGit;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("This is a test.");
		TestGit test = new TestGit();
		test.setName("Joshua");
		
		System.out.println("Ceci est le nom de la class TestGit : " + test.getName());
	}

}
