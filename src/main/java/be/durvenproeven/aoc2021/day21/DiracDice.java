package be.durvenproeven.aoc2021.day21;

import java.util.List;

public class DiracDice {
	private Player currentPlayer;
	private int positionPlayer1;
	private int positionPlayer2;
	private final int max;

	private int score1 = 0;
	private int score2 = 0;

	public DiracDice(int startPlayer1, int startPlayer2, int max) {
		this.positionPlayer1 = startPlayer1;
		this.positionPlayer2 = startPlayer2;
		this.currentPlayer = Player.PLAYER1;
		this.max = max;
	}

	public DiracDice withOtherPlayer(int newPosition, int newScore){
		return new DiracDice(
				currentPlayer.next(),
				getPositionForPlayer(newPosition, Player.PLAYER1),
				getPositionForPlayer(newPosition, Player.PLAYER2),
				max,
				getScoreForPlayer(newScore, Player.PLAYER1),
				getScoreForPlayer(newScore, Player.PLAYER2)
		);
	}

	private int getScoreForPlayer(int newScore, Player player1) {
		if (currentPlayer==player1){
			return newScore;
		}
		return currentPlayer==Player.PLAYER1?score1: score2;
	}

	private int getPositionForPlayer(int newPosition, Player player1) {
		if (currentPlayer==player1){
			return newPosition;
		}
		return currentPlayer==Player.PLAYER1?positionPlayer1: positionPlayer2;
	}

	public DiracDice(Player currentPlayer, int positionPlayer1, int positionPlayer2, int max, int score1, int score2) {
		this.currentPlayer = currentPlayer;
		this.positionPlayer1 = positionPlayer1;
		this.positionPlayer2 = positionPlayer2;
		this.max = max;
		this.score1 = score1;
		this.score2 = score2;
	}

	public boolean roll(DeterministicDice dice, int scoreToReach) {
		int nextRolls = dice.getNextRolls(3);
		boolean hasWon = false;
		if( currentPlayer==Player.PLAYER1) {
			positionPlayer1 = getNextScore(nextRolls, positionPlayer1);
			score1 += positionPlayer1;
			hasWon= score1>= scoreToReach;
		} else {
			positionPlayer2 = getNextScore(nextRolls, positionPlayer2);
			score2 += positionPlayer2;
			hasWon= score2> scoreToReach;
		}
		currentPlayer= currentPlayer.next();
		return hasWon;
	}

	public int rollTillWinner(DeterministicDice dice, int scoreToReach){
		while(!roll(dice, scoreToReach));
		if( currentPlayer==Player.PLAYER1){
			return score1;
		}
		return score2;
	}

	private int getNextScore(int nextRolls, int startPlayer1) {
		int nextScore = startPlayer1 + nextRolls;
		if (nextScore%max==0){
			return max;
		}
		return (startPlayer1 + nextRolls) % max;
	}

	public int getScoreCurrentPlayer(){
		if (currentPlayer==Player.PLAYER1){
			return score1;
		}
		return score2;
	}

	public int getLocationCurrentPlayer(){
		if (currentPlayer==Player.PLAYER1){
			return positionPlayer1;
		}
		return positionPlayer2;
	}

	public List<Integer> getScores(){
		return List.of(score1, score2);
	}


	enum Player {
		PLAYER1, PLAYER2;

		Player next() {
			if (this == PLAYER1) {
				return PLAYER2;
			}
			return PLAYER1;
		}
		}
}
