package data.domain;

import java.math.BigDecimal;
import java.util.Comparator;

public final class Lender {

    private final String name;
    private final BigDecimal interest_rate;
    private final BigDecimal amount;



    public Lender(String name, BigDecimal interest_rate, BigDecimal amount) {
        this.name = name;
        this.interest_rate = interest_rate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getInterest_rate() {
        return interest_rate;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", interest_rate=" + interest_rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lender lender = (Lender) o;

        if (name != null ? !name.equals(lender.name) : lender.name != null) return false;
        if (interest_rate != null ? !interest_rate.equals(lender.interest_rate) : lender.interest_rate != null)
            return false;
        return amount != null ? amount.equals(lender.amount) : lender.amount == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (interest_rate != null ? interest_rate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
