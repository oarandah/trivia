package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class Simula_basededatos {
	LinkedList popQuestions = new LinkedList();
	LinkedList scienceQuestions = new LinkedList();
	LinkedList sportsQuestions = new LinkedList();
	LinkedList rockQuestions = new LinkedList();

	public Simula_basededatos() {
		for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast("Rock Question " + i);
		}
	}
	
	public Object obtenPregunta(String genre) {
		if (genre == "Pop") {
			return popQuestions.removeFirst();
		}
		else if (genre == "Science") {
			return scienceQuestions.removeFirst();
		}
		else if (genre == "Sports") {
			return sportsQuestions.removeFirst();
		}
		else if (genre == "Rock") {
			return rockQuestions.removeFirst();
		}
		else {
			return "";
		}
	}

}
