import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NullFilterringStreamsTest {

    @Test
    void filteringStreamsForTheNullValuesLambdas() {
        List<String> testList = createATestListWithNulls();

        List<?> resultList = NullFilteringStreams.filterNullValuesLambdas(testList);

        assertEquals(7, resultList.size());
    }

    @Test
    void filteringStreamsForTheNullValuesObjects() {
        List<String> testList = createATestListWithNulls();

        List<?> resultList = NullFilteringStreams.filterNullValuesObjects(testList);

        assertEquals(7, resultList.size());
    }

    @Test
    void filteringShouldNotAcceptNullLists() {
        List<String> nullList = null;
        assertThrows(IllegalArgumentException.class, () -> NullFilteringStreams.filterNullValuesLambdas(nullList));
        assertThrows(IllegalArgumentException.class, () -> NullFilteringStreams.filterNullValuesObjects(nullList));
    }

    private List<String> createATestListWithNulls() {
        List<String> list = new ArrayList<String>();
        list.add("Poland");
        list.add(null);
        list.add("Germany");
        list.add("");
        list.add("France");
        list.add("UK");
        list.add("\\ugly\\stuff\n\t\\e\r\t\\notevensurewhat'sthat\"");
        list.add("USA");
        return list;
    }
}