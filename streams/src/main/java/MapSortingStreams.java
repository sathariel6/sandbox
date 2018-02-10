import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class MapSortingStreams {

    private MapSortingStreams() {
    }

    public static Map<String, String> sortMapByKey(Map<String, String> unsortedMap) {
        sanitizeInput(unsortedMap);
        Map<String, String> sortedByKey = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> sortedByKey.put(e.getKey(), e.getValue()));
        return sortedByKey;
    }

    public static Map<String, String> sortMapByValue(Map<String, String> unsortedMap) {
        sanitizeInput(unsortedMap);
        Map<String, String> sortedByValueMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> sortedByValueMap.put(e.getKey(), e.getValue()));
        return sortedByValueMap;
    }

    public static Map<String, String> sortMapByKey_ReverseOrder(Map<String, String> unsortedMap) {
        sanitizeInput(unsortedMap);
        Map<String, String> sortedByKey_Reversed = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).forEachOrdered(e -> sortedByKey_Reversed.put(e.getKey(), e.getValue()));
        return sortedByKey_Reversed;
    }

    public static Map<String, String> sortMapByValue_ReverseOrder(Map<String, String> unsortedMap) {
        sanitizeInput(unsortedMap);
        Map<String, String> sortedByValue_Reversed = new LinkedHashMap<>();
        unsortedMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(e -> sortedByValue_Reversed.put(e.getKey(), e.getValue()));
        return sortedByValue_Reversed;
    }

    private static void sanitizeInput(Map<String, String> unsortedMap) {
        if (unsortedMap == null) {
            throw new IllegalArgumentException("Map passed cannot be null");
        }
        unsortedMap.keySet().removeIf(Objects::isNull);
        unsortedMap.values().removeIf(Objects::isNull);
    }
}
