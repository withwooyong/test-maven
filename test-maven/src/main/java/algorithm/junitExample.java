package algorithm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class junitExample {

	@Test
	public void assertArrayEqualsTest() {
		int[] a = { 1, 2, 3, 5 };
		int[] b = { 1, 2, 3, 5 };
		assertArrayEquals(a, b);
	}
	
	@Test
	public void assertEqualsTest() {
		String a = "test";
		String b = "test";
		assertEquals(a, b);
	}
	
	@Test
	public void assertSameTest() {
		String a = "test";
		String b = "test";
		assertSame(a, b);
	}
	
	@Test
	public void assertTrueTest() {
		boolean a = false;
		assertTrue(a);
	}
}
