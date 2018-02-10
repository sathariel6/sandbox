import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MapSortingStreamsTest {

    private final String[] COUNTRIES = {"France", "Germany", "Poland", "Russia", "UK", "USA"};
    private final String[] CAPITALS = {"Berlin", "London", "Moscow", "Paris", "Warsaw", "Washington"};
    private final String[] CAPITALS_WHEN_SORTED_BY_COUNTRIES = {"Paris", "Berlin", "Warsaw", "Moscow", "London", "Washington"};
    private final String[] COUNTRIES_WHEN_SORTED_BY_CAPITALS = {"Germany", "UK", "Russia", "France", "Poland", "USA"};
    private final String[] COUNTRIES_WHEN_SORTED_BY_CAPITALS_REVERSE_ORDER = {"USA", "Poland", "France", "Russia", "UK", "Germany"};
    private final String[] CAPITALS_WHEN_SORTED_BY_COUNTRIES_REVERSE_ORDER = {"Washington", "London", "Moscow", "Warsaw", "Berlin", "Paris"};

    private Map<String, String> testMap;

    @BeforeEach
    void setUp() {
        testMap = createAnUnsortedMap();
    }

    @Test
    void testMapSortingByKey() {
        Map<String, String> sortedMap = MapSortingStreams.sortMapByKey(testMap);
        List<String> keys = new ArrayList<>(sortedMap.keySet());
        List<String> values = new ArrayList<>(sortedMap.values());

        assertIterableEquals(createASortedList(CAPITALS), keys);
        assertIterableEquals(createAListInArrayOrder(COUNTRIES_WHEN_SORTED_BY_CAPITALS), values);
    }

    @Test
    void testMapSortingByValues() {
        Map<String, String> sortedMap = MapSortingStreams.sortMapByValue(testMap);
        List<String> keys = new ArrayList<>(sortedMap.keySet());
        List<String> values = new ArrayList<>(sortedMap.values());

        assertIterableEquals(createASortedList(COUNTRIES), values);
        assertIterableEquals(createAListInArrayOrder(CAPITALS_WHEN_SORTED_BY_COUNTRIES), keys);
    }

    @Test
    void testMapSortingByKey_ReverseOrder() {
        Map<String, String> sortedMap = MapSortingStreams.sortMapByKey_ReverseOrder(testMap);
        List<String> keys = new ArrayList<>(sortedMap.keySet());
        List<String> values = new ArrayList<>(sortedMap.values());

        assertIterableEquals(createASortedList_ReverseOrder(CAPITALS), keys);
        assertIterableEquals(createAListInArrayOrder(COUNTRIES_WHEN_SORTED_BY_CAPITALS_REVERSE_ORDER), values);
    }

    @Test
    void testMapSortingByValue_ReverseOrder() {
        Map<String, String> sortedMap = MapSortingStreams.sortMapByValue_ReverseOrder(testMap);
        List<String> keys = new ArrayList<>(sortedMap.keySet());
        List<String> values = new ArrayList<>(sortedMap.values());

        assertIterableEquals(createASortedList_ReverseOrder(COUNTRIES), values);
        assertIterableEquals(createAListInArrayOrder(CAPITALS_WHEN_SORTED_BY_COUNTRIES_REVERSE_ORDER), keys);
    }

    @Test
    void testSanitizingTheInput() {
        testMap = null;

        assertThrows(IllegalArgumentException.class, () -> MapSortingStreams.sortMapByKey(testMap));
        assertThrows(IllegalArgumentException.class, () -> MapSortingStreams.sortMapByValue(testMap));
        assertThrows(IllegalArgumentException.class, () -> MapSortingStreams.sortMapByKey_ReverseOrder(testMap));
        assertThrows(IllegalArgumentException.class, () -> MapSortingStreams.sortMapByValue_ReverseOrder(testMap));
    }

    @Test
    void sortingMethodsCanHandleInvalidElementsInTheMap() {
        addDodgyElements(testMap);

        MapSortingStreams.sortMapByValue_ReverseOrder(testMap);
        MapSortingStreams.sortMapByValue(testMap);
        MapSortingStreams.sortMapByKey(testMap);
        MapSortingStreams.sortMapByKey_ReverseOrder(testMap);
    }

    private Map<String, String> createAnUnsortedMap() {
        Map<String, String> countriesMap = new HashMap<>();
        countriesMap.put("Warsaw", "Poland");
        countriesMap.put("Berlin", "Germany");
        countriesMap.put("Paris", "France");
        countriesMap.put("Moscow", "Russia");
        countriesMap.put("London", "UK");
        countriesMap.put("Washington", "USA");
        return countriesMap;
    }

    private List<String> createAListInArrayOrder(String[] strings) {
        return Arrays.asList(strings);
    }

    private List<String> createASortedList(String[] strings) {
        List<String> listOfStrings = Arrays.asList(strings);
        Collections.sort(listOfStrings);
        return listOfStrings;
    }

    private List<String> createASortedList_ReverseOrder(String[] strings) {
        List<String> listOfStrings = Arrays.asList(strings);
        Collections.sort(listOfStrings, Collections.reverseOrder());
        return listOfStrings;
    }

    private void addDodgyElements(Map<String, String> map) {
        map.put("", "wrongCountry");
        map.put(null, "evenWorseOne");
        map.put("key", null);
    }

}