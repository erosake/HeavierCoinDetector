package eros.detector;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static utilities.Utils.generateCoinsWithOneFakeCoinAmongThem;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import eros.data.Coin;
import eros.data.interfaces.Weightable;
import io.vavr.Tuple2;

@DisplayName("Heavier coin detection...")
public class HeavierCoinDetectorTest {

	@DisplayName("Testing the detection of the heavier coin...")
	@ParameterizedTest(name = "{index} => {arguments}")
	@MethodSource("coinsProvider")
	public void test(Tuple2<Coin, List<Weightable>> tuplu) {

		var heavierCoin = tuplu._1();
		List<Weightable> coins = tuplu._2();

		var detectedCoin = new HeavierCoinDetector<Weightable>().detect(coins)
			.get().orElse(Coin.NONE);

		assertTrue(heavierCoin == detectedCoin,
			() -> "It should be the heavierCoin.");

		assertTrue(heavierCoin.hashCode() == detectedCoin.hashCode(),
			() -> "They should have the same signature.");

	}

	static Stream<Arguments> coinsProvider() {

		return IntStream.range(0, 100).mapToObj(
			x -> arguments(generateCoinsWithOneFakeCoinAmongThem(x, 20)));

	}

}
