package blockchain.data.loaders;

import blockchain.data.domain.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileDataLoader implements DataLoader {

    private static final String COMMA_DELIMITER = ",";

    public Map<Integer, Map<Token, Double>> loadUserData() {

        Map<Integer, Map<Token, Double>> records = new ConcurrentHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data/RiskEngineTestDataSet.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Map<Token, Double> balances = new ConcurrentHashMap<>();
                balances.put(Token.USD, Double.valueOf(values[1]));
                balances.put(Token.EUR, Double.valueOf(values[2]));
                balances.put(Token.BTC, Double.valueOf(values[3]));
                balances.put(Token.BCH, Double.valueOf(values[4]));
                balances.put(Token.ETH, Double.valueOf(values[5]));

                records.put(Integer.valueOf(values[0]), balances);
            }
        } catch (Exception e) {
            System.out.println("ERROR LOADING FILE");
            e.printStackTrace();
        }

        return records;
    }
}
