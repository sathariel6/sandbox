import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NullFilteringStreamsTest {

    private List<String> testList;

    @BeforeEach
    void setUp() {
        testList = createATestListWithNulls();
    }

    @Test
    void filteringStreamsForTheNullValuesLambdas() {
        List<?> resultList = NullFilteringStreams.filterNullValuesLambdas(testList);

        assertEquals(7, resultList.size());
    }

    @Test
    void filteringStreamsForTheNullValuesObjects() {
        List<?> resultList = NullFilteringStreams.filterNullValuesObjects(testList);

        assertEquals(7, resultList.size());
    }

    @Test
    void filteringShouldNotAcceptNullLists() {
        testList = null;
        assertThrows(IllegalArgumentException.class, () -> NullFilteringStreams.filterNullValuesLambdas(testList));
        assertThrows(IllegalArgumentException.class, () -> NullFilteringStreams.filterNullValuesObjects(testList));
    }

    private List<String> createATestListWithNulls() {
        List<String> list = new ArrayList<>();
        list.add("Poland");
        list.add(null);
        list.add("Germany");
        list.add("");
        list.add("France");
        list.add("UK");
        list.add("\\ugly\\stuff\n\t\\e\r\t\\notEvenSure\"");
        list.add("USA");
        return list;
    }
}