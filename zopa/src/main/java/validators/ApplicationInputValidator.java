package validators;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApplicationInputValidator {
    public static final int MAXIMUM_ALLOWED_LOAN = 15000;
    public static final int MINIMUM_ALLOWED_LOAN = 1000;
    public static final int ALLOWED_LOAN_MULTIPLIER = 100;

    public static void validateInputFormat(String[] input) throws InputValidationException {
        if (input == null || input.length != 2 ) {
            throw new InputValidationException("Application needs 2 arguments: data file location and requested loan amount!");
        }
    }

    public static void validateFileInput(String filePath) throws InputValidationException {
        if (Files.notExists(Paths.get(filePath))) {
            throw  new InputValidationException("File data doesn't exist in provided path!");
        }
    }

    public static void validateLoanAmount(String requestedAmount) throws InputValidationException {
        BigDecimal amount;
        try {
            amount = new BigDecimal(requestedAmount);
        } catch (Exception e) {
            throw  new InputValidationException("Requested amount has to be in numerical value");
        }
        if (amount.intValue() < MINIMUM_ALLOWED_LOAN || amount.intValue() > MAXIMUM_ALLOWED_LOAN) {
            throw new InputValidationException("Requested amount has to be between 1000 and 15000.");
        }
        if (amount.intValue() % ALLOWED_LOAN_MULTIPLIER != 0) {
            throw new InputValidationException("Requested amount has to be described in the 100s.");
        }

    }

    public static void validateFundsAvailable(BigDecimal loanAmountAsString, BigDecimal fundsAvailable)  throws InputValidationException {
        if (loanAmountAsString.compareTo(fundsAvailable) > 0 ) {
            throw new InputValidationException(String.format("Requested amount: %s is higher than available funds of: %s", loanAmountAsString, fundsAvailable));
        }
    }
}
