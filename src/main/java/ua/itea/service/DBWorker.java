package ua.itea.service;

import ua.itea.models.Product;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBWorker {
    private Connection conn = null;
    private PreparedStatement ps;
//    String url = "jdbc:mysql://s3.thehost.ua/itea2?" +
//            "user=helen&password=123456";
    private String url = "jdbc:mysql://%s/%s?user=%s&password=%s";
    private final static String CHECK_LOGIN = "select name from users where login = ? and password = ?";
    private final static String GET_USERS = "select * from users";
    private final static String INSERT_USER =
            "insert into users " +
            "(login, password, name, age, gender, address, comment) " +
            "values " +
            "(?,?,?,?,?,?,?)";
    private final static String UPDATE_PASSWORD_MD5 = "update users set password = ? where login = ?";

    ResourceBundle config;

    public DBWorker() {
        try {
            config = ResourceBundle.getBundle("config." + ResourceBundle.getBundle("config.config").getString("config"));
            String host = config.getString("host");
            String db = config.getString("db");
            String user = config.getString("user");
            String psw = config.getString("psw");
            url = String.format(url, host, db, user, psw);
        } catch (MissingResourceException e) {
            System.err.println("Missing resource in /WEB-INF/config/config\nLoading from standart");
            String host = "localhost";
            String db = "dbitea";
            String user = "mysql";
            String psw = "mysql";
            url = String.format(url, host, db, user, psw);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connection....");
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Failure...");
            System.out.println("SQLException " + e.getMessage());
            System.out.println("SQLState " + e.getSQLState());
            System.out.println("VendorError..." + e.getErrorCode());
        }
        System.out.println("Connection obtained");
    }

    public String getUsers() {
        StringBuilder buf = new StringBuilder();
        buf.append("<table border='1'>");
        try {

            ResultSet rs = ps.executeQuery(GET_USERS);
            buf.append("<tr><td>Login</td><td>");
            buf.append("Name").append("</td><td>");
            buf.append("Age").append("</td><td>");
            buf.append("Gender").append("</td><td>");
            buf.append("Address").append("</td><td>");
            buf.append("Comment");
            buf.append("</td></tr>");
            while(rs.next()) {
                buf.append("<tr><td>")
                        .append(rs.getString(2)).append("</td><td>");
                buf.append(rs.getString(4)).append("</td><td>");
                buf.append(rs.getInt(5)).append("</td><td>");
                buf.append(rs.getString(6)).append("</td><td>");
                buf.append(rs.getString(7)).append("</td><td>");
                buf.append(rs.getString(8));
                buf.append("</td></tr>");
            }
            buf.append("</table>");
            System.out.println(buf.toString());
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public boolean checkUserByLogin(String login) {
        final String sql = String.format("select * from users where login = '%s'",login);
        try {
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean checkLogin(String login,String password) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(CHECK_LOGIN);
            ps.setString(1,login);
            ps.setString(2,hashString(password));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreperedStatement(ps);
        }
        return false;
    }

    private void closePreperedStatement(PreparedStatement ps) {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String registerUser(
            String login,
            String password,
            String name,
            String age,
            String gender,
            String address,
            String comment
    ) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_USER);
            ps.setString(1,login);
            ps.setString(2,hashString(password));
            ps.setString(3,name);
            ps.setInt(4,Integer.parseInt(age));
            ps.setString(5,gender);
            ps.setString(6,address);
            ps.setString(7,comment);
            ps.execute();

            return "true";
        } catch (SQLException e) {
            e.printStackTrace();
            return "<font color = 'red'>SQLException : " + e.getMessage() + " SQLState : " + e.getSQLState() +"</font>";
        }
    }

    public void close() {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updatePasswordForUser(String login) {
        if (checkUserByLogin(login)) {
            try {
                ps = conn.prepareStatement("select password from users where login = ?");
                ps.setString(1,login);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String password = rs.getString(1);

                ps = conn.prepareStatement(UPDATE_PASSWORD_MD5);
                ps.setString(1,hashString(password));
                ps.setString(2,login);
                ps.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreperedStatement(ps);
            }
        }
        return false;
    }

    public boolean updatePasswordForUser(String login,String password) {
        if (checkUserByLogin(login)) {
            try {
                ps = conn.prepareStatement(UPDATE_PASSWORD_MD5);
                ps.setString(1,hashString(password));
                ps.setString(2,login);
                ps.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreperedStatement(ps);
            }
        }
        return false;
    }

    private String hashString (String hash) {
        MessageDigest md5 = null;
        final String salt = "dbitea";
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Add salt here hask + salt
        md5.update(StandardCharsets.UTF_8.encode(hash + salt));
        return String.format("%032x", new BigInteger(md5.digest()));
    }

    final static private String SELECT_ALL = "SELECT * FROM products";
    final static private String SELECT_BY_CATEGORY = "SELECT * FROM products where category = ?";
    final static private String SELECT_BY_ID = "SELECT * FROM products where id = ?";

    public Product getProductById(int id) {
        try {
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setDescription(rs.getString(4));
                p.setCategory(rs.getInt(5));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreperedStatement(ps);
        }
        return null;
    }

    public List<Product> getProductsByCategory(int category) {
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(SELECT_BY_CATEGORY);
            ps.setInt(1, category);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setDescription(rs.getString(4));
                p.setCategory(rs.getInt(5));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreperedStatement(ps);
        }
        return products;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(SELECT_ALL);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setDescription(rs.getString(4));
                p.setCategory(rs.getInt(5));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreperedStatement(ps);
        }
        return products;
    }
}
