import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import ua.itea.controllers.ProductController;

public class DBWorkerTests {

    ProductController dbWorker;

    @Before
    public void before() {
        dbWorker = new ProductController();
    }

    @After
    public void after() {  }

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
    }

    private void print(Object ... str) {
        for (Object s : str) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
