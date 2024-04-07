
import java.util.List;
import java.util.stream.Collectors;

/**
 * You should complete the function in this class
 * <p>
 * Feel free to define any method and / or class you want
 */
class CollectionTest {


    /**
     * operation : x -> ((x * 2) + 3) ^ 5
     */
    public static List<Double> compute1(List<Integer> input) {
        return input.stream().mapToDouble(x->((x * 2) + 3) ^ 5).boxed().toList();

    }

    /**
     * operation : abc -> AbcAbc
     */
    public static List<String> compute2(List<String> input) {
        return input.stream().map(c->c.replace("a","A")).toList();
    }

}