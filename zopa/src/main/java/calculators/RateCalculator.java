package calculators;

import data.LendingPool;
import data.domain.Lender;
import data.domain.LoanDetails;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RateCalculator {

    public BigDecimal calculateLoanRate (BigDecimal requestedAmount, LendingPool lendingPool) {
        List<Lender> lenders = lendingPool.sortLendersByLowestRate();
        List<BigDecimal> weightedList = createWeightedList(requestedAmount, lenders);

        // calculate rate by: summing up rates and dividing by size
        return  weightedList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(weightedList.size()), MathContext.DECIMAL64);
    }

    public BigDecimal calculateMonthlyPayment(BigDecimal requestedAmount, BigDecimal annualInterestRate, int termOfLoanInMonths) {
        BigDecimal interestRatePerPeriod = annualInterestRate.divide(new BigDecimal("12"), RoundingMode.HALF_EVEN);
        BigDecimal numerator = interestRatePerPeriod.multiply((new BigDecimal("1").add(interestRatePerPeriod)).pow(termOfLoanInMonths));
        BigDecimal denominator = ((new BigDecimal("1").add(interestRatePerPeriod)).pow(termOfLoanInMonths)).subtract(new BigDecimal("1"));

        return requestedAmount.multiply(numerator.divide(denominator, RoundingMode.HALF_EVEN));
    }

    public BigDecimal calculateTotalPaymentForMonthlyRates(BigDecimal monthlyRate, int termOfLoanInMonths) {
        return monthlyRate.multiply(new BigDecimal(termOfLoanInMonths));
    }

    private List<BigDecimal> createWeightedList(BigDecimal requestedAmount, List<Lender> lenders) {
        // create array of requested amount size
        List<BigDecimal> weightedList = new ArrayList<>();

        // populate each element of the weighted list with the rates available until full
        for (int lendersIndex = 0; lendersIndex < lenders.size() ; lendersIndex ++) {
            Lender currentLender = lenders.get(lendersIndex);
            for (int amountIndex = 0; amountIndex < currentLender.getAmount().intValue(); amountIndex++) {
                if (weightedList.size() >= requestedAmount.intValue()) {
                    break;
                }
                weightedList.add(currentLender.getInterest_rate());
            }
        }
        return weightedList;
    }
}
