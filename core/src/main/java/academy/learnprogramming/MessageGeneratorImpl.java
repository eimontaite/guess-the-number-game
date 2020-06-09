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
	private static final String WON = "game.won";
	private static final String LOST = "game.lost";
	private static final String INVALID_RANGE = "game.invalid.range";
	private static final String FIRST_GUESS = "game.first.guess";
	private static final String LOWER = "game.lower";
	private static final String HIGHER = "game.higher";
	private static final String REMAINING_GUESSES = "game.remaining.guesses";

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
			return getMessage(WON, number);
		} else if(loss) {
			return getMessage(LOST, number);
		} else if(!validRange) {
			return getMessage(INVALID_RANGE);
		} else if(canGuess) {
			return getMessage(FIRST_GUESS);
		} else {
			String direction = getMessage(LOWER);
			;
			if(goHigher) {
				direction = getMessage(HIGHER);
			}
			return getMessage(REMAINING_GUESSES, direction, remainingGuesses);
		}
	}

	// == private methods ==
	private String getMessage(String code, Object... args) {
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());

	}
}
