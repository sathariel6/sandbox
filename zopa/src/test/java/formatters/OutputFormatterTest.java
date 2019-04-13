package formatters;

import data.domain.LoanDetails;
import org.junit.Test;
import validators.InputValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class OutputFormatterTest {


    @Test
    public void outputIsBeingScaled() {
        BigDecimal requestedAmount = new BigDecimal("1232131");
        BigDecimal testRate = new BigDecimal("0.02301293021321083908390218321908321");
        BigDecimal testMonthlyPayment = new BigDecimal("212.213120321321321");
        BigDecimal totalPayment = new BigDecimal("2132312.3123213213231");

        LoanDetails details = new LoanDetails(new BigDecimal("1232131"), testRate, testMonthlyPayment, totalPayment);

        BigDecimal testRateScaled = testRate.multiply(new BigDecimal("100")).setScale(1, RoundingMode.HALF_EVEN);
        BigDecimal testMonthlyPaymentScaled = testMonthlyPayment.setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal testTotalPaymentScaled = totalPayment.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(OutputFormatter.produceOutput(details));
        assertEquals(String.format(OutputFormatter.outputFormat, requestedAmount, testRateScaled, testMonthlyPaymentScaled, testTotalPaymentScaled),
                OutputFormatter.produceOutput(details));
    }

    @Test
    public void producingErrorMessageReadsEntryFromException() {
        InputValidationException testException = new InputValidationException("Hello World");
        assertEquals(testException.getMessage(), OutputFormatter.produceErrorMessage(testException));
    }
}