import java.util.*;
import java.util.concurrent.CompletableFuture;

class AsyncTest {

    private static List<Enterprise> enterprises = List.of(
            new Enterprise("ent_1", "Google", "ceo_2"),
            new Enterprise("ent_2", "Facebook", "ceo_1")
    );

    private static List<Ceo> ceos = List.of(
            new Ceo("ceo_1", "Mark"),
            new Ceo("ceo_2", "Sundar"),
            new Ceo("ceo_3", "Bill")
    );

    public static CompletableFuture<Optional<Ceo>> getCeoById(String ceo_id) {
        return CompletableFuture.supplyAsync(() -> ceos.stream().filter(ceo -> Objects.equals(ceo.id, ceo_id)).findFirst());
    }

    public static CompletableFuture<Optional<Enterprise>> getEnterpriseByCeoId(String ceo_id) {
        return CompletableFuture.supplyAsync(() -> enterprises.stream().filter(ceo -> Objects.equals(ceo.ceo_id, ceo_id)).findFirst());
    }

    public static CompletableFuture<Map<Optional<Ceo>, Optional<Enterprise>>> getCEOAndEnterprise(String ceo_id) {
        return CompletableFuture.allOf(getCeoById(ceo_id), getEnterpriseByCeoId(ceo_id)).thenApply(voided ->
                new HashMap<Optional<Ceo>, Optional<Enterprise>>() {{
                    put(getCeoById(ceo_id).join(), getEnterpriseByCeoId(ceo_id).join());
                }});
    }


    public static void test() {


        getCeoById("ceo_01").thenAccept(ceo -> {
            if (ceo.isPresent()) {
                System.out.println("THE CEO IS " + ceo.get());
            } else {
                System.out.println("THE CEO DOESN'T EXIST SORRY ");

            }
        });
        getEnterpriseByCeoId("ceo_01").thenAccept(enterprise -> {
            if (enterprise.isPresent()) {
                System.out.println("THE ENTERPRISE IS " + enterprise.get());
            } else {
                System.out.println("THE ENTERPRISE DOESN'T EXIST SORRY ");

            }
        });


        getCEOAndEnterprise("ceo_1").join().forEach((ceo, enterprise) -> {
            if (ceo.isPresent() && enterprise.isPresent()) {
                System.out.println("CEO IS " + ceo.get() + " OF ENTERPRISE " + enterprise.get());
            } else System.out.println("DOEST FOUNT");
        });
    }

}