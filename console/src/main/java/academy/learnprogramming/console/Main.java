package academy.learnprogramming.console;

import academy.learnprogramming.AppConfig;
import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import academy.learnprogramming.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		log.info("Guess The Number Game");

		// create the context (container)
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// get the number generator bean from the container
		NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

		// call next() to get a random number
		int number = numberGenerator.next();

		// log the number
		log.info("number = {}", number);

		// get the game bean from the container
		Game game = context.getBean(Game.class);

		// get the message generator bean from the container
		MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

		// call the MessageGenerator methods
		log.info(messageGenerator.getMainMessage());
		log.info(messageGenerator.getResultMessage());

		// close the container
		context.close();

	}
}
