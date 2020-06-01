package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static final String CONFIG_LOCATION = "beans.xml";

	public static void main(String[] args) {
		log.info("Guess The Number Game");

		// create the context (container)
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

		// get the number generator bean from the container
		NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);

		// call next() to get a random number
		int number = numberGenerator.next();

		// log the number
		log.info("number = {}", number);

		// get the game bean from the container
		Game game = context.getBean("game", Game.class);

		// close the container
		context.close();

	}
}
