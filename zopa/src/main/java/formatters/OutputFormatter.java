package formatters;

import data.domain.LoanDetails;
import validators.InputValidationException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class OutputFormatter {

    public static final String outputFormat = "Requested amount: £%s\nRate: %%%s\nMonthly Repayment: £%s\nTotal Repayment: £%s\n";


    public static String produceOutput(LoanDetails loanDetails) {
        return String.format(outputFormat,
                loanDetails.getRequestedAmount(),
                loanDetails.getInterestRate().multiply(new BigDecimal("100")).setScale(1, RoundingMode.HALF_EVEN),
                loanDetails.getMonthlyRepayment().setScale(2, RoundingMode.HALF_EVEN),
                loanDetails.getTotalRepayment().setScale(2, RoundingMode.HALF_EVEN));
    }

    public static String produceErrorMessage(InputValidationException e) {
        return e.getMessage();
    }

    public static void writeMessage(String output) {
        System.out.println(output);
    }
}
