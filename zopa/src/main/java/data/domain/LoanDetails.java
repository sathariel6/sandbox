package data.domain;

import java.math.BigDecimal;

public final class LoanDetails {

    private BigDecimal requestedAmount;
    private BigDecimal interestRate;
    private BigDecimal monthlyRepayment;
    private BigDecimal totalRepayment;

    public LoanDetails(BigDecimal requestedAmount, BigDecimal interestRate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }
}
