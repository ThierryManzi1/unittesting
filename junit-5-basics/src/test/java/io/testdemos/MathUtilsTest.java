package io.testdemos;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@DisplayName("When running MathUtils")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //with this annotation you don't need static on BeforeAll method as the instance is only created once
class MathUtilsTest {
	MathUtils mathUtils;
	TestInfo testInfo;//dependency injection
	TestReporter testReporter;//dependency injection
	//must be static
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo= testInfo;
		this.testReporter= testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
		
	}
	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up..");
	}
	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest{
		@Test
		@DisplayName("testing add method for positive")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1,1),"should return the right sum");
		}
		@Test
		@DisplayName("testing add method for negative")
		void testAddNegative() {
			int expected = -2;
			int actual= mathUtils.add(-1, -1);
			assertEquals(expected, actual,()->"should return the right sum"+expected+" but return "+actual);
		}
	}
	@Test
	@Tag("circle")
	//@RepeatedTest(3) //for running tests 3 times
	void testComputeCircleArea() {
		//repetitionInfo.
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10),"should return right circle area");
	}
	
	@Test
	@Tag("Math")
	@DisplayName("multiply method")
	void testMultiply() {
		//int expected =4;
		//int actual = mathUtils.add(2,2);
		//assertEquals(expected, actual,"should return the right product");
		//System.out.println();
		
		assertAll(
				()->assertEquals(4, mathUtils.multiply(2,2)),
				()->assertEquals(0, mathUtils.multiply(2,0)),
				()->assertEquals(-2, mathUtils.multiply(2,-1))
				);
	}
	
	@Test
	@Tag("Math")
	@DisplayName("divide method")
	void testDivide() {
		//boolean isServerUp =false;
		//assumeTrue(isServerUp);
		assertThrows(ArithmeticException.class , ()->mathUtils.divide(1, 0),"divide by zero should throw");
		
	}
	@Test
	@Disabled
	@DisplayName("TDD method. Should not run")
	void testDisabled() {
		fail("This test should be disabled");
	}

}
