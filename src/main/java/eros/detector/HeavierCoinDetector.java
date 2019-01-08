package eros.detector;

import java.util.List;
import java.util.Optional;

import eros.balance.PanBalance;
import eros.balance.PanBalance.Response;
import eros.data.interfaces.Weightable;
import eros.util.Util;
import eros.util.tailcall.TailCall;
import eros.util.tailcall.TailCalls;

public class HeavierCoinDetector<T extends Weightable> {

	private static final int NUMBER_OF_PARTITIONS = 3;

	public TailCall<Optional<T>> detect(List<T> things) {

		int size = things.size();
		while (size > 1) {

			PanBalance<T> panBalance = new PanBalance<T>();

			List<List<T>> partitions = Util.partition(things,
				NUMBER_OF_PARTITIONS);

			int firstPartitionIndex = 0;
			int secondPartitionIndex = 1;
			int thirdPartitionIndex = 2;

			if (size % NUMBER_OF_PARTITIONS == 1) {

				firstPartitionIndex = 1;
				secondPartitionIndex = 2;
				thirdPartitionIndex = 0;

			}

			List<T> firstPartition = partitions.get(firstPartitionIndex);
			List<T> secondPartition = partitions.get(secondPartitionIndex);
			List<T> thirdPartition = partitions.get(thirdPartitionIndex);

			firstPartition.forEach(x -> panBalance.addItemToLeftPan(x));
			secondPartition.forEach(x -> panBalance.addItemToRightPan(x));

			Response whichPaneIsHeavier = panBalance.whichPaneIsHeavier();
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
