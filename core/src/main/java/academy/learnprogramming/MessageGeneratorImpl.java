package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {
	// == constants ==
	private static final String MAIN_MESSAGE = "game.main.message";
	// == fields ==
	private final Game game;
	private final MessageSource messageSource;

	@Autowired
	public MessageGeneratorImpl(Game game, MessageSource messageSource) {
		this.game = game;
		this.messageSource = messageSource;
	}

	// == init ==
	@PostConstruct
	public void init() {
		log.info("Value of the game: {}", game);
	}

	// == public methods ==
	@Override
	public String getMainMessage() {
		return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
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

	// == private methods ==
	private String getMessage(String code, Object... args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());

	}
}
