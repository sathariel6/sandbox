package calculators;

import data.LendingPool;
import data.domain.Lender;
import org.apache.commons.lang3.math.Fraction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RateCalculatorTest {

    private RateCalculator rateCalculator;

    @Before
    public void setUp() throws Exception {
        rateCalculator = new RateCalculator();
    }

    @Test
    public void testWeightedRateGetsCalculatedForAmount() {
        BigDecimal requestedAmount = new BigDecimal("5");

        Lender first = new Lender("First", new BigDecimal("1"), new BigDecimal("1"));
        Lender second = new Lender ("second", new BigDecimal("2"), new BigDecimal("1"));
        Lender third = new Lender ("third",new BigDecimal("4") , new BigDecimal("3"));

        LendingPool lendingPool = new LendingPool(Arrays.asList(third, first, second));

        assertEquals(new BigDecimal("3"), rateCalculator.calculateLoanRate(requestedAmount, lendingPool));

    }

    @Test
    public void testWeightedRateStopsOnceReachingTheAmount() {
        BigDecimal requestedAmount = new BigDecimal("2");

        Lender first = new Lender("First", new BigDecimal("1"), new BigDecimal("1"));
        Lender second = new Lender ("second", new BigDecimal("2"), new BigDecimal("1"));
        Lender third = new Lender ("third",new BigDecimal("4") , new BigDecimal("3"));

        LendingPool lendingPool = new LendingPool(Arrays.asList(third, first, second));

        assertEquals(new BigDecimal("1.5"), rateCalculator.calculateLoanRate(requestedAmount, lendingPool));
    }

    @Test
    public void testTotalAmountIsReturnedCorrectly() {
        BigDecimal monthlyRate = new BigDecimal("100");
        int term = 10;

        assertEquals(new BigDecimal ("1000"), rateCalculator.calculateTotalPaymentForMonthlyRates(monthlyRate, term));
    }

    @Test
    public void testCalculateMonthlyPaymentForFormula() {
        BigDecimal annualRate = new BigDecimal("0.12");
        BigDecimal requestedAmount = new BigDecimal ("100");
        int timeOfLoan = 1;

        assertEquals(0, new BigDecimal("101").compareTo(rateCalculator.calculateMonthlyPayment(requestedAmount, annualRate, timeOfLoan)));
    }

    @Test
    @Ignore
    public void testUseCase() {
        BigDecimal requestedAmount = new BigDecimal("1000");

        Lender bob = new Lender("Bob", new BigDecimal("0.075"), new BigDecimal("640"));
        Lender jane = new Lender ("Jane", new BigDecimal("0.069"), new BigDecimal("480"));
        Lender fred = new Lender ("Fred", new BigDecimal("0.071"), new BigDecimal("520"));
        Lender mary = new Lender ("Mary", new BigDecimal("0.104"), new BigDecimal("170"));
        Lender john = new Lender ("John", new BigDecimal("0.081"), new BigDecimal("320"));
        Lender dave = new Lender ("Dave", new BigDecimal("0.074"), new BigDecimal("140"));
        Lender angela = new Lender ("Angela", new BigDecimal("0.071"), new BigDecimal("60"));

        LendingPool lendingPool = new LendingPool(Arrays.asList(bob, jane, fred, mary, john, dave, angela));

        assertEquals(new BigDecimal("0.07004"), rateCalculator.calculateLoanRate(requestedAmount, lendingPool));

        BigDecimal monthlyPayment = rateCalculator.calculateMonthlyPayment(requestedAmount, new BigDecimal("0.07"), 36);
        BigDecimal totalPayment = rateCalculator.calculateTotalPaymentForMonthlyRates(monthlyPayment, 36);

        assertEquals(new BigDecimal("1108.10"), totalPayment);
        assertEquals(new BigDecimal("30.78"), monthlyPayment);

    }
}