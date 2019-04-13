package blockchain.data.loaders;

import blockchain.data.domain.Token;

import java.util.Map;

public interface DataLoader {

        Map<Integer, Map<Token, Double>> loadUserData();
}
