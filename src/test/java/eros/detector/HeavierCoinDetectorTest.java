package eros.detector;

import eros.data.Coin;
import eros.data.interfaces.Weightable;
import io.vavr.Tuple2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static utilities.Utils.generateCoinsWithOneFakeCoinAmongThem;

@SuppressWarnings("ExcessiveLambdaUsage")
@DisplayName("Heavier coin detection...")
class HeavierCoinDetectorTest {

    static Stream<Arguments> coinsProvider() {

        return IntStream.range(0, 100).mapToObj(
                x -> arguments(generateCoinsWithOneFakeCoinAmongThem(x, 20)));

    }

    @DisplayName("Testing the detection of the heavier coin...")
    @ParameterizedTest(name = "{index} => {arguments}")
    @MethodSource("coinsProvider")
    void test(Tuple2<Coin, List<Weightable>> tuplu) {

        var heavierCoin = tuplu._1();
        var coins = tuplu._2();

        var detectedCoin = new HeavierCoinDetector<>().detect(coins)
                .get().orElse(Coin.NONE);

        assertSame(heavierCoin, detectedCoin, () -> "It should be the heavierCoin.");

        assertEquals(heavierCoin.hashCode(), detectedCoin.hashCode(), () -> "They should have the same signature.");

    }

}
