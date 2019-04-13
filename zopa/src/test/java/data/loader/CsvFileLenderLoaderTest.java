package data.loader;

import data.LendingPool;
import data.domain.Lender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.BiFunction;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class CsvFileLenderLoaderTest {

    private static final String csvDataAsString = "Lender,Rate,Available\n" +
            "Bob,0.075,640\n" +
            "Jane,0.069,480\n";

    private static final LendingPool lendingPool = new LendingPool(
            Arrays.asList(
                    new Lender("Bob", new BigDecimal("0.075"), new BigDecimal("640")),
                    new Lender("Jane", new BigDecimal("0.069"), new BigDecimal("480"))
            ));

    @Test
    public void loadingFileWorks() {
        CsvFileLenderLoader fileDataLoader = new CsvFileLenderLoader();
        Path csvDataTempFile = null;
        try {
            csvDataTempFile = Files.createTempFile("data", "csv");
            Files.write(csvDataTempFile, csvDataAsString.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LendingPool loadedPool = fileDataLoader.loadLenderData(csvDataTempFile);

        assertEquals(loadedPool, lendingPool);



    }

}