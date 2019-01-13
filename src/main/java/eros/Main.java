package eros;

import com.google.common.base.Stopwatch;
import eros.data.Coin;
import eros.detector.HeavierCoinDetector;
import eros.generators.CoinGenerator;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) {

        var coinTester = new Main();
        Runnable consumer = coinTester::extracted;

        test(consumer);

    }

    private static void test(Runnable runnable) {

        var createStarted = Stopwatch.createStarted();

        runnable.run();

        System.out.println(createStarted.stop());

    }

    private static void accept(CompletableFuture<Optional<Coin>> x) {
        try {
            System.out.println(x.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static CompletableFuture<Optional<Coin>> detectHeavierCoinInTheGroup(
            int x) {

        return CompletableFuture.supplyAsync(() -> {

            var coins = new CoinGenerator().generateCoinsWithOneHeavierCoinAmongThem(x);
            var heavierCoinDetector = new HeavierCoinDetector<Coin>();
            Collections.shuffle(coins);
            // System.out.println(coins);

            // System.out.println(heavierCoinDetector.detect(coins).get());
            // System.out.println(
            // "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

            return heavierCoinDetector.detect(coins).get();

        });

    }

    private void extracted() {
        var list = IntStream.rangeClosed(0, 10000)
                .mapToObj(Main::detectHeavierCoinInTheGroup)
                .collect(Collectors.toList());

        var allOf = CompletableFuture
                .allOf(list.toArray(new CompletableFuture[0]));
        allOf.join();

        list.forEach(Main::accept);
    }

}
