package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    private final NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {5, 109, 983})
    void isPrimeForPrimes(int argument) {
        assertTrue(numberWorker.isPrime(argument));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 111, 742})
    void isPrimeForNotPrimes(int argument) {
        assertFalse(numberWorker.isPrime(argument));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1})
    void isPrimeForIncorrectNumbers(int argument) {
        assertThrows(IllegalNumberException.class, () -> {numberWorker.isPrime(argument);});
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    void testDigitsSum(int number, int sum) {
        assertEquals(sum, numberWorker.digitSum(number));
    }
}
