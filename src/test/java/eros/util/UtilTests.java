package eros.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing the Util class...")
public class UtilTests {

	@Test
	@DisplayName("Testing the partition method...")
	public void partitionTest() {

		List<List<Object>> partition = Util.partition(Collections.emptyList(),
			5);

		assertTrue(!partition.isEmpty(), "partition is not empty.");
		assertTrue(partition.size() == 5, "size should be 5.");
		IntStream.rangeClosed(0, 4).forEach(
			x -> assertTrue(partition.get(x).isEmpty(), "Size should be 0"));
	}

}
