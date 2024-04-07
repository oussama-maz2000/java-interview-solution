import java.util.Optional;

public class BasicTest {

    /**
     * return i ^ n for positive Integer, None otherwise
     * alse return None in case of errors
     */
    public static Optional<Integer> power(Integer i, Integer n) {
        if (i <= 0 || n <= 0) return Optional.empty();
        if (n % 2 == 0) {
            if (n == 2) return Optional.of(i * i);
            return power(i, n / 2);
        } else {
            if (power(i, n - 1).isPresent()) {
                int i1 = power(i, n - 1).get() * i;
                return Optional.of(i1);
            }

        }
        return Optional.empty();

    }
}