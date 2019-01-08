package eros;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.Stopwatch;

import eros.data.Coin;
import eros.detector.HeavierCoinDetector;
import eros.generators.CoinGenerator;

public class Main {

	public static void main(String[] args) {

		Main coinTester = new Main();
		Runnable consumer = () -> coinTester.extracted();

		test(consumer);

	}

	private static void test(Runnable runnable) {

		Stopwatch createStarted = Stopwatch.createStarted();

		runnable.run();

		System.out.println(createStarted.stop());

	}

	public void extracted() {
		List<CompletableFuture<Optional<Coin>>> list = IntStream
			.rangeClosed(0, 10000).mapToObj(x -> detectHeavierCoinInTheGroup(x))
			.collect(Collectors.toList());

		CompletableFuture<Void> allOf = CompletableFuture
			.allOf(list.toArray(new CompletableFuture[list.size()]));
		allOf.join();

		list.forEach(x -> {
			try {
				System.out.println(x.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}

	private static CompletableFuture<Optional<Coin>> detectHeavierCoinInTheGroup(
		int x) {

		return CompletableFuture.supplyAsync(() -> {

			List<Coin> coins = new CoinGenerator().generate(x);
			HeavierCoinDetector<Coin> heavierCoinDetector = new HeavierCoinDetector<Coin>();
			Collections.shuffle(coins);
			// System.out.println(coins);

			// System.out.println(heavierCoinDetector.detect(coins).get());
			// System.out.println(
			// "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

			return heavierCoinDetector.detect(coins).get();

		});

	}

}
