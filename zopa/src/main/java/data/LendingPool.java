package data;

import data.domain.Lender;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LendingPool {

    private List<Lender> lenders;
    public static final Comparator<Lender> lowestRateComparator = Comparator.comparing(Lender::getInterest_rate);

    public LendingPool(List<Lender> lenders) {
        this.lenders = lenders;
    }

    public BigDecimal totalAmountAvailable () {
        return lenders.stream().map(Lender::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Lender> sortLendersByLowestRate() {
        return lenders.stream().sorted(lowestRateComparator).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "LendingPool{" +
                "lenders=" + lenders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LendingPool that = (LendingPool) o;

        return lenders != null ? lenders.equals(that.lenders) : that.lenders == null;
    }

    @Override
    public int hashCode() {
        return lenders != null ? lenders.hashCode() : 0;
    }
}
