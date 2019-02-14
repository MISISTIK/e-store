import org.junit.After;
import org.junit.Before;
import ua.itea.service.DBWorker;
import org.junit.Test;

public class DBWorkerTests {

    DBWorker dbWorker;

    @Before
    public void before() {
        dbWorker = new DBWorker();
    }

    @After
    public void after() {
        dbWorker.close();
    }

    @Test
    public void test1() {
        dbWorker.getProducts().forEach(System.out::println);
        print();
        dbWorker.getProductsByCategory(1).forEach(System.out::println);
        print();
        dbWorker.getProductsByCategory(2).forEach(System.out::println);
        print();
        dbWorker.getProductsByCategory(3).forEach(System.out::println);
        print();
        print(dbWorker.getProductById(1));
        dbWorker.close();
    }

    private void print(Object ... str) {
        for (Object s : str) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
