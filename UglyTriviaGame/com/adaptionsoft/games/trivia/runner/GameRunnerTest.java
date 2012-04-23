package com.adaptionsoft.games.trivia.runner;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Simula_basededatos;

public class GameRunnerTest {
	static GameRunner gr = null;
	static Game aGame = null;
	static ArrayList players_forced = null;
	static long seed = 2;
	EspiaSalida salidaResultados;
	
	@Before
	public void inicializar () {
		gr = new GameRunner();
		aGame = new Game();
		players_forced = new ArrayList();
		
		players_forced.add("Pepe");
		players_forced.add("Lola");		
		
		salidaResultados = new EspiaSalida();
		System.setOut(salidaResultados);
		
	}
	
	@Test
	public void GameRunner_players_number_Test () {

		
		gr.gameRunnerUsers_Roll(aGame, players_forced, seed);
		
		ArrayList players = aGame.getPlayers();
		int[] purses = aGame.getPurses();
		
		Assert.assertEquals(2, aGame.howManyPlayers());
		Assert.assertEquals(players_forced.get(0), players.get(0));

	}
	
	@Test
	public void GameRunner_first_user_Pepe_Test () {

		
		gr.gameRunnerUsers_Roll(aGame, players_forced, seed);
		
//		System.out.println("Cuantos jugadores: " + aGame.howManyPlayers());

		ArrayList players = aGame.getPlayers();
		int[] purses = aGame.getPurses();
		
		Assert.assertEquals(players_forced.get(0), players.get(0));
		
	}
	
	@Test
	public void GameRunner_second_user_Lola_Test () {

		
		gr.gameRunnerUsers_Roll(aGame, players_forced, seed);
		
//		System.out.println("Cuantos jugadores: " + aGame.howManyPlayers());

		ArrayList players = aGame.getPlayers();
		int[] purses = aGame.getPurses();
		
		Assert.assertEquals(players_forced.get(1), players.get(1));
		
	}
	
	@Test
	public void GameRunner_Pepe_score_6_Test () {
	
		gr.gameRunnerUsers_Roll(aGame, players_forced, seed);
		
		ArrayList players = aGame.getPlayers();
		int[] purses = aGame.getPurses();
		
		Assert.assertEquals(6, purses[0]);
		
		Assert.assertTrue(salidaResultados.contains("Pop Question 4"));
		Assert.assertTrue(salidaResultados.contains("Answer was corrent!!!!"));
		Assert.assertTrue(salidaResultados.contains("Pepe now has 6 Gold Coins."));
	}
		
	@Test
	public void GameRunner_Pepe_score_6_Test_con_preguntas_custom() {
		gr.gameRunnerUsers_Roll(aGame, players_forced, seed);
		
		ArrayList players = aGame.getPlayers();
		int[] purses = aGame.getPurses();
		Simula_basededatos mockQuestionsDB = Mockito.mock(Simula_basededatos.class);
		Mockito.when(mockQuestionsDB.obtenPregunta(Mockito.anyString())).thenReturn("This is a custom question");
		
		aGame.setSimula_basededatos(mockQuestionsDB);
		
		Assert.assertTrue(salidaResultados.contains("Pop Question 4"));
		Assert.assertTrue(salidaResultados.contains("Answer was corrent!!!!"));
		Assert.assertTrue(salidaResultados.contains("Pepe now has 6 Gold Coins."));
	}	
}

class EspiaSalida extends PrintStream {
	List<String> buffer = new ArrayList<String>();
	
	public EspiaSalida() {
		super(Mockito.mock(OutputStream.class));
	}
	
	public void println(String str) {
		buffer.add(str);
	}
	
	public void println(Object obj) {
		buffer.add(obj.toString());
	}
	
	public boolean contains(String str) {
		return buffer.contains(str);
	}
}