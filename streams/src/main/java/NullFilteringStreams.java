import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class NullFilteringStreams {

    private NullFilteringStreams() {}

    public static List<?> filterNullValuesLambdas(List<?> stringList) {
        return stringList.stream().filter(str -> str != null).collect(Collectors.toList());
    }


    public static List<?> filterNullValuesObjects(List<?> stringList) {
        return stringList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

}
