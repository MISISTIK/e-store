import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.itea.dao.products.JpaProductsDao;
import ua.itea.dao.user.JpaUserDao;
import ua.itea.models.Product;
import ua.itea.models.User;
import ua.itea.service.JpaFactory;
import ua.itea.utils.Utils;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HibernateTests {

    private EntityManager em;
    JpaUserDao udao;
    JpaProductsDao pdao;

    @Before
    public void before() {
        em = JpaFactory.getEntityManager();
        udao = new JpaUserDao();
        pdao = new JpaProductsDao();
    }

    @After
    public void after() {
        em.close();
    }

    @Test
    public void registerUserTest() {
        User user = new User("hello@test.ua", Utils.hashString("123"), "test_name", 28, "Male", "Ukraine");
        assertEquals(udao.registerUser(user), true);
    }

    @Test
    public void checkUserByLoginTest() {
        assertEquals(udao.checkUserByLogin("admin@itea.ua"), true);
        assertEquals(udao.checkUserByLogin("noname"), false);
    }

    @Test
    public void getUserByIdTest() {
        User user = udao.getUserById(1);
        print(user);
    }

    @Test
    public void getUserByLoginTest() {
        User user = udao.getUserByLogin("admin@itea.ua");
        print(user);
    }

    @Test
    public void getProductByIdTest() {
        Product p = pdao.getProductById(10);
        print(p);
    }

    @Test
    public void getProductByCategoryTest() {
        List<Product> list =  pdao.getProductsByCategory(1);
        list.forEach(System.out::println);
    }


    @Test
    public void getProductsTest() {
        List<Product> list =  pdao.getProducts();
        list.forEach(System.out::println);
    }

    private void print(Object... str) {
        for (Object s : str) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
