package utilities;

import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eros.data.Coin;
import io.vavr.Tuple2;

public class Utils {

	public static <T> List<T> generateListOfRandomElementsWithSize(
		IntFunction<T> mapper, int size) {

		return IntStream.range(0, size).mapToObj(mapper)
			.collect(Collectors.toList());

	}

	public static Tuple2<Coin, List<Coin>> generateCoinsWithOneFakeCoinAmongThem(
		int totalNumberOfCoins, double weightOfOneCoin) {

		var result = generateNumberOfIdenticalCoins(totalNumberOfCoins,
			weightOfOneCoin);
		var heavierCoin = new Coin(weightOfOneCoin + 1.0d);
		result.add(heavierCoin);

		Collections.shuffle(result);

		return new Tuple2<Coin, List<Coin>>(heavierCoin, result);

	}

	public static List<Coin> generateNumberOfIdenticalCoins(
		int numberOfIdenticalCoins, double weightOfOneCoin) {

		IntFunction<? extends Coin> toCoin = x -> new Coin(weightOfOneCoin);
		return IntStream.range(0, numberOfIdenticalCoins).mapToObj(toCoin)
			.collect(Collectors.toList());

	}

}
