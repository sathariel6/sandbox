import calculators.RateCalculator;
import data.LendingPool;
import data.domain.LoanDetails;
import data.loader.CsvFileLenderLoader;
import data.loader.LenderLoader;
import formatters.OutputFormatter;
import validators.ApplicationInputValidator;
import validators.InputValidationException;

import java.math.BigDecimal;
import java.nio.file.Paths;

import static validators.ApplicationInputValidator.validateFundsAvailable;

public class LoanDetailsProvider {

    private static final int TERM_OF_LOAN_IN_MONTHS = 36;

    public static void main(String[] args) {
        String output;
        try {
            validateInputs(args);
        } catch (InputValidationException e) {
            output = OutputFormatter.produceErrorMessage(e);
            OutputFormatter.writeMessage(output);
            System.exit(0);
        }

        String fileDataLocation = args[0];
        String loanAmountAsString = args[1];
        LenderLoader loader = new CsvFileLenderLoader();
        LendingPool lendingPool = loader.loadLenderData(Paths.get(fileDataLocation));

        BigDecimal requestedAmount = new BigDecimal(loanAmountAsString);

        try {
            validateFundsAvailable(requestedAmount, lendingPool.totalAmountAvailable());
        } catch (InputValidationException e) {
            output = OutputFormatter.produceErrorMessage(e);
            OutputFormatter.writeMessage(output);
            System.exit(0);
        }

        RateCalculator rateCalculator = new RateCalculator();
        BigDecimal loanRate = rateCalculator.calculateLoanRate(requestedAmount, lendingPool);
        BigDecimal monthlyRate = rateCalculator.calculateMonthlyPayment(requestedAmount, loanRate, TERM_OF_LOAN_IN_MONTHS);
        BigDecimal totalRate = rateCalculator.calculateTotalPaymentForMonthlyRates(monthlyRate, TERM_OF_LOAN_IN_MONTHS);

        LoanDetails loanDetails = new LoanDetails(requestedAmount, loanRate, monthlyRate, totalRate);

        output = OutputFormatter.produceOutput(loanDetails);
        OutputFormatter.writeMessage(output);
    }

    private static void validateInputs(String[] args) throws InputValidationException {
        ApplicationInputValidator.validateInputFormat(args);
        ApplicationInputValidator.validateFileInput(args[0]);
        ApplicationInputValidator.validateLoanAmount(args[1]);
    }
}
