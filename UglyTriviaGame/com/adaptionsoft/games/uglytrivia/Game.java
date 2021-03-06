package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	private Simula_basededatos pregunta_bbdd = new Simula_basededatos();
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public void setSimula_basededatos(Simula_basededatos simula_preguntaBBDD) {
		this.pregunta_bbdd = simula_preguntaBBDD;
	}

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	/**
	 * Return true if the game is playable.
	 * 
	 * @return true if the game is playable.
	 */
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
		
		
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		System.out.println(pregunta_bbdd.obtenPregunta(currentCategory()));
	}
	
  public static void main(String[] args) {
    System.out.println("Hello World!"); // Display the string.
  }

  public String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}

	public static class SimpleSingleton {
    private static SimpleSingleton singleInstance =  new SimpleSingleton();
 
    //Marking default constructor private
    //to avoid direct instantiation.
    private SimpleSingleton() {
    }
 
    //Get instance for class SimpleSingleton
    public static SimpleSingleton getInstance() {
 
        return singleInstance;
  }
}
	/**
	 * Tells if the last player won.
	 */
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	public ArrayList getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList players) {
		this.players = players;
	}

	public int[] getPlaces() {
		return places;
	}

	public void setPlaces(int[] places) {
		this.places = places;
	}

	public int[] getPurses() {
		return purses;
	}

	public void setPurses(int[] purses) {
		this.purses = purses;
	}

	public boolean[] getInPenaltyBox() {
		return inPenaltyBox;
	}

	public void setInPenaltyBox(boolean[] inPenaltyBox) {
		this.inPenaltyBox = inPenaltyBox;
	}

	public LinkedList getPopQuestions() {
		return popQuestions;
	}

	public void setPopQuestions(LinkedList popQuestions) {
		this.popQuestions = popQuestions;
	}

	public LinkedList getScienceQuestions() {
		return scienceQuestions;
	}

	public void setScienceQuestions(LinkedList scienceQuestions) {
		this.scienceQuestions = scienceQuestions;
	}

	public LinkedList getSportsQuestions() {
		return sportsQuestions;
	}

	public void setSportsQuestions(LinkedList sportsQuestions) {
		this.sportsQuestions = sportsQuestions;
	}

	public LinkedList getRockQuestions() {
		return rockQuestions;
	}

	public void setRockQuestions(LinkedList rockQuestions) {
		this.rockQuestions = rockQuestions;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isGettingOutOfPenaltyBox() {
		return isGettingOutOfPenaltyBox;
	}

	public void setGettingOutOfPenaltyBox(boolean isGettingOutOfPenaltyBox) {
		this.isGettingOutOfPenaltyBox = isGettingOutOfPenaltyBox;
	}
}
