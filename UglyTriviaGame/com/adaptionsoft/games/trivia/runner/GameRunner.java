
package com.adaptionsoft.games.trivia.runner;
import java.util.ArrayList;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;
	
	public static void main(String[] args) {

		
	}
	
	public static void gameRunnerUsers_Roll (Game aGame, ArrayList players_forced, long seed) {
		
		
		for (int i = 0; i < players_forced.size(); i++) {
			aGame.add(players_forced.get(i).toString());
		}
		
//		aGame.add("Chet");
//		aGame.add("Pat");
//		aGame.add("Sue");
		
		Random rand = new Random(seed);
	
		do {
			
			aGame.roll(rand.nextInt(5) + 1);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
			
			
			
		} while (notAWinner);
		
		
	}
}
