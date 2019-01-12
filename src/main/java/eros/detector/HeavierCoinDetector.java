package eros.detector;

import eros.balance.PanBalance;
import eros.data.interfaces.Weightable;
import eros.util.Util;
import eros.util.tailcall.TailCalls;
import eros.util.tailcall.interfaces.TailCall;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("LoopStatementThatDoesntLoop")
public class HeavierCoinDetector<T extends Weightable> {

    private static final int NUMBER_OF_PARTITIONS = 3;

    public TailCall<Optional<T>> detect(List<T> things) {

        var size = things.size();
        while (size > 1) {

            var panBalance = new PanBalance<T>();

            var partitions = Util.partition(things, NUMBER_OF_PARTITIONS);

            var firstPartitionIndex = 0;
            var secondPartitionIndex = 1;
            var thirdPartitionIndex = 2;

            if (size % NUMBER_OF_PARTITIONS == 1) {

                firstPartitionIndex = 1;
                secondPartitionIndex = 2;
                thirdPartitionIndex = 0;

            }

            var firstPartition = partitions.get(firstPartitionIndex);
            var secondPartition = partitions.get(secondPartitionIndex);
            var thirdPartition = partitions.get(thirdPartitionIndex);

            firstPartition.forEach(panBalance::addItemToLeftPan);
            secondPartition.forEach(panBalance::addItemToRightPan);

            var whichPaneIsHeavier = panBalance.whichPaneIsHeavier();
            switch (whichPaneIsHeavier) {
                case LEFT:

                    return () -> detect(firstPartition);

                case RIGTH:

                    return () -> detect(secondPartition);

                case EQUAL:

                    return () -> detect(thirdPartition);

            }

        }

        if (things.isEmpty()) {

            return TailCalls.done(Optional.empty());

        }

        return TailCalls.done(Optional.ofNullable(things.get(0)));

    }

}
