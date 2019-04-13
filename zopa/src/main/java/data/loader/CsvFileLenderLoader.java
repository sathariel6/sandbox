package data.loader;

import data.LendingPool;
import data.domain.Lender;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CsvFileLenderLoader implements LenderLoader {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\\n";

    public LendingPool loadLenderData(Path fileLocation) {
        List<Lender> lenders = new ArrayList<>();
        try {
            byte[] fileBytes = Files.readAllBytes(fileLocation);
            String data = new String(fileBytes);
            List<String> linesList = new LinkedList<>(Arrays.asList(data.split(NEW_LINE_SEPARATOR)));
            linesList.remove(0);

            for (String line : linesList) {
                String[] values = line.split(COMMA_DELIMITER);
                String name = values[0].trim();
                BigDecimal interestRate = new BigDecimal(values[1].trim());
                BigDecimal amount = new BigDecimal(values[2].trim());
                Lender lender = new Lender(name, interestRate, amount);
                lenders.add(lender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LendingPool(lenders);
    }

}
