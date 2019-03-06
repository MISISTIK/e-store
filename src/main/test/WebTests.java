import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebTests {

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    public void test1() throws IOException {
//        URL url = new URL("http://172.17.13.250/itea/demo.php");
        URL url = new URL("http://google.com");
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        String param = "name=Daniel";
        urlConn.setRequestProperty("Content-Length",String.valueOf(param.getBytes().length));
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        urlConn.setRequestMethod("GET");

        try (OutputStream out = urlConn.getOutputStream();
             InputStream in = urlConn.getInputStream()

        ) {
            out.write(param.getBytes(StandardCharsets.UTF_8));
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void spring_context() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");

    }

    private void print(Object ... str) {
        for (Object s : str) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
