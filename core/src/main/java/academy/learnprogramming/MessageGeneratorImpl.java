package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {

	// == constants ==
	private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

	// == fields ==
	@Autowired
	private Game game;

	// == public methods ==
	@PostConstruct
	public void init() {
		log.info("Value of the game: {}", game);
	}

	// == public methods ==
	@Override
	public String getMainMessage() {
		return "Number is between " +
				game.getSmallest() +
				" and " +
				game.getBiggest() +
				". Can you guess it?";
	}

	@Override
	public String getResultMessage() {
		int number = game.getNumber();
		boolean victory = game.isGameWon();
		boolean loss = game.isGameLost();
		boolean validRange = game.isValidNumberRange();
		boolean canGuess = game.getRemainingGuesses() == game.getGuessCount();
		int remainingGuesses = game.getRemainingGuesses();
		boolean goHigher = game.getGuess() < game.getNumber();

		if(victory) {
			return "You won! The number was: " + number;
		} else if(loss) {
			return "You lost... The number was: " + number;
		} else if(!validRange) {
			return "Invalid number range!";
		} else if(canGuess) {
			return "Your first guess: ";
		} else {
			String direction = "Lower";
			if(goHigher) {
				direction = "Higher";
			}
			return direction + "! You have " + remainingGuesses + " guess(es) left";
		}
	}
}
