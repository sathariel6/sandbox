package validators;

import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationInputValidatorTest {

    @Test (expected = InputValidationException.class)
    public void argumentValidationFailsForOneArgumentPassed() throws InputValidationException {
        String[] arguments = {"Hello"};
        ApplicationInputValidator.validateInputFormat(arguments);
    }

    @Test (expected = InputValidationException.class)
    public void argumentValidationFailsForThreeArgumentsPassed() throws InputValidationException {
        String[] arguments = {"Hello", "World", "!!"};
        ApplicationInputValidator.validateInputFormat(arguments);
    }

    @Test
    public void argumentValidationPassesForTwoArgumentsPassed() throws InputValidationException {
        String[] arguments = {"Hello", "World"};
        ApplicationInputValidator.validateInputFormat(arguments);
    }

    @Test (expected = InputValidationException.class)
    public void argumentValidationFailsForNull() throws InputValidationException {
        ApplicationInputValidator.validateInputFormat(null);
    }

    @Test (expected = InputValidationException.class)
    public void loanAmountValidationThrowsExceptionForValuesBelow1000() throws InputValidationException {
        ApplicationInputValidator.validateLoanAmount("999");
    }

    @Test (expected = InputValidationException.class)
    public void loanAmountValidationFailsForValuesNondivisbleBy100() throws InputValidationException {
        ApplicationInputValidator.validateLoanAmount("1001");
    }

    @Test (expected = InputValidationException.class)
    public void loanAmountValidationWorksFor1000() throws InputValidationException {
        ApplicationInputValidator.validateLoanAmount("15001");
    }

    @Test
    public void loanAmountValidationPassesForBorderCases() throws InputValidationException{
        ApplicationInputValidator.validateLoanAmount("1000");
        ApplicationInputValidator.validateLoanAmount("15000");
    }

    @Test(expected = InputValidationException.class)
    public void loanAmountValidationWontAcceptNonNumericalValue() throws InputValidationException {
        ApplicationInputValidator.validateLoanAmount("Hello");
    }

    @Test(expected = InputValidationException.class)
    public void fileValidationFailsForNonExistentFile() throws InputValidationException {
        ApplicationInputValidator.validateLoanAmount("SurelyFileWithThisNameWontExistInClasspath!!");
    }

    @Test
    public void fileValidationPassesIfTheFileExists() throws Exception {
        Path csvDataTempFile = Files.createTempFile("data", "csv");
        ApplicationInputValidator.validateFileInput(csvDataTempFile.toString());
    }

    @Test (expected = InputValidationException.class)
    public void requestedAmountValidationFailsIfNotEnoughFunds() throws InputValidationException {
        ApplicationInputValidator.validateFundsAvailable(new BigDecimal("2.01"), new BigDecimal("2.00"));
    }

    @Test
    public void requestedAmountValidationPassesIfFundsEqualsOrLargerThanRequestedAmount() throws InputValidationException {
        ApplicationInputValidator.validateFundsAvailable(new BigDecimal("2.00"), new BigDecimal("2.01"));
    }

    @Test
    public void requestedAmountValidationPassesIfFundsAndValuesAreEqual() throws InputValidationException {
        ApplicationInputValidator.validateFundsAvailable(new BigDecimal("2.00"), new BigDecimal("2.000000000000"));
    }
}