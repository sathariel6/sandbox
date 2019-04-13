package blockchain.server;

import blockchain.data.domain.Token;

public class IncomingMessage {
    private int userId;
    private Token token;

    public int getUserId() {
        return userId;
    }

    public Token getToken() {
        return token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
