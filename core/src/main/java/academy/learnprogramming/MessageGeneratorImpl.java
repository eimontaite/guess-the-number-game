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
	private int guessCount = 10;

	// == public methods ==
	@PostConstruct
	public void init() {
		log.info("Value of the game: {}", game);
	}
	@Override
	public String getMainMessage() {
		return "Calling getMainMessage()";
	}

	@Override
	public String getResultMessage() {
		return "Calling getResultMessage(). " + (game.isGameWon() ? "You won!" : "You lost...");
	}
}
