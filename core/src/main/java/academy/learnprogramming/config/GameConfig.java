package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties")
public class GameConfig {
	// == fields ==
	@Value("${game.maxNumber}")
	private int maxNumber;
	@Value("${game.minNumber}")
	private int minNumber;
	@Value("${game.guessCount}")
	private int guessCount;

	// == bean methods ==
	@Bean
	@MaxNumber
	public int maxNumber() {
		return maxNumber;
	}

	@Bean
	@MinNumber
	public int minNumber() {
		return minNumber;
	}


	@Bean
	@GuessCount
	public int guessCount() {
		return guessCount;
	}
}
