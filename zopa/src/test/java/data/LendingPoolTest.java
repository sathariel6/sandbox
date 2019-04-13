package data;

import data.domain.Lender;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LendingPoolTest {

    @Test
    public void helperSortsOutLendersByLowestRate() {
        Lender first = new Lender("First", new BigDecimal(10), new BigDecimal(100));
        Lender second = new Lender ("Second", new BigDecimal(0), new BigDecimal(1000));
        Lender third = new Lender("Third", new BigDecimal(20), new BigDecimal(10));

        LendingPool initialPool = new LendingPool(Arrays.asList(first, second, third));

        List<Lender> sortedListOfLenders = Arrays.asList(second, first, third);

        assertEquals(initialPool.sortLendersByLowestRate(), sortedListOfLenders);
    }

    @Test
    public void sortingLendersDoesntAffectInitialPool() {
        Lender first = new Lender("First", new BigDecimal(10), new BigDecimal(100));
        Lender second = new Lender ("Second", new BigDecimal(0), new BigDecimal(1000));
        Lender third = new Lender("Third", new BigDecimal(20), new BigDecimal(10));

        LendingPool initialPool = new LendingPool(Arrays.asList(first, second, third));
        LendingPool expectedPool = new LendingPool(Arrays.asList(first, second, third));

        initialPool.sortLendersByLowestRate();

        assertEquals(expectedPool, initialPool);
    }

    @Test
    public void totalAmountIsCalculatedCorrectly() {
        Lender first = new Lender("First", new BigDecimal(10), new BigDecimal(100));
        Lender second = new Lender ("Second", new BigDecimal(0), new BigDecimal(1000));

        LendingPool lendingPool = new LendingPool(Arrays.asList(first, second));

        assertEquals(new BigDecimal(1100), lendingPool.totalAmountAvailable());
    }
}