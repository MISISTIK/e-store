import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.itea.dao.products.JpaProductsDao;
import ua.itea.dao.user.JpaUserDao;
import ua.itea.models.Product;
import ua.itea.models.User;
import ua.itea.service.JpaFactory;
import static ua.itea.utils.Utils.*;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CheckoutTests {

    private EntityManager em;
    JpaProductsDao pdao;

    @Before
    public void before() {
        em = JpaFactory.getEntityManager();
        pdao = new JpaProductsDao();
    }

    @After
    public void after() {
        em.close();
    }

    private final static int MAX_WIDTH = 32;
    private final static String h1 = repeatStr("-",MAX_WIDTH);

    @Test
    public void getProductsTest() {
        List<Product> list =  pdao.getProducts();

        StringBuffer bf = new StringBuffer();
        bf.append("<b>").append(alignCenter("E-store",MAX_WIDTH)).append("</b>").append("\n");
        bf.append(alignSides("Checkout for: ","MISISTIK" ,MAX_WIDTH)).append("\n");
        bf.append(h1).append("\n");

        for(Product p : list) {
            bf.append(alignCenter("12 x " + p.getPrice(), MAX_WIDTH)).append("\n");
            bf.append(alignSides(p.getName(), "480", MAX_WIDTH)).append("\n");
        }
        bf.append(h1).append("\n");
        bf.append(alignSides("Discount","0",MAX_WIDTH)).append("\n");
        bf.append("<b>").append(alignSides("TOTAL: ","1240",MAX_WIDTH)).append("</b>").append("\n");
        print(bf.toString());
    }

    @Test
    public void string() {
        StringBuffer bf = new StringBuffer();
        bf.append(alignCenter("<b>E-store</b>",MAX_WIDTH)).append("\n");
        bf.append(alignSides("Checkout for: ","<b>MISISTIK</b>" ,MAX_WIDTH)).append("\n");
        bf.append(h1).append("\n");
        bf.append(alignCenter("12 x 40:",MAX_WIDTH)).append("\n");
        bf.append(alignSides("tovar1","480",MAX_WIDTH)).append("\n");
        bf.append(h1).append("\n");
        bf.append(alignSides("Discount","0",MAX_WIDTH)).append("\n");
        bf.append(alignSides("TOTAL: ","<b>1240</b>",MAX_WIDTH)).append("\n");


        print(bf.toString());
    }

    private void print(Object... str) {
        for (Object s : str) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
