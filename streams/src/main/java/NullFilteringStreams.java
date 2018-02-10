import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

final class NullFilteringStreams {

    private NullFilteringStreams() {
    }

    public static List<?> filterNullValuesLambdas(List<?> stringList) {
        validateInputList(stringList);
        return stringList.stream().filter(str -> str != null).collect(Collectors.toList());
    }

    public static List<?> filterNullValuesObjects(List<?> stringList) {
        validateInputList(stringList);
        return stringList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static void validateInputList(List<?> stringList) {
        if (stringList == null) {
            throw new IllegalArgumentException("Passed list cannot be null.");
        }
    }

}
