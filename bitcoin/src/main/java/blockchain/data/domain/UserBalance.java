package blockchain.data.domain;

import java.util.Map;

public class UserBalance {

    private final int userId;
    private final Map<Token, Double> balance;

    public UserBalance(int userId, Map<Token, Double> balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
