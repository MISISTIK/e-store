package ua.itea.dao.user;

import org.apache.commons.dbutils.DbUtils;
import ua.itea.models.Product;
import ua.itea.models.User;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private final static String CHECK_LOGIN = "select name from users where login = ? and password = ?";
    private final static String CHECK_USER = "select * from users where login = ?";
    private final static String INSERT_USER =
        "insert into users (login, password, name, age, gender, address) values (?,?,?,?,?,?)";

    private final static String UPDATE_PASSWORD_MD5 = "update users set password = ? where login = ?";

    public MySqlUserDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        PreparedStatement ps = null;
        User user = new User();
        try {
            ps = conn.prepareStatement("SELECT * FROM users where id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        PreparedStatement ps = null;
        User user = new User();
        try {
            ps = conn.prepareStatement("SELECT * FROM users where login = ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);
        }
        return null;
    }

    @Override
    public boolean checkUserByLogin(String login) {
        try {
            ps = conn.prepareStatement(CHECK_USER);
            ps.setString(1,login);
            ps.executeQuery();
            rs = ps.getResultSet();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }

    public boolean checkLogin(String login,String password) {
        try {
            ps = conn.prepareStatement(CHECK_LOGIN);
            ps.setString(1,login);
            ps.setString(2,hashString(password));
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }

    public boolean registerUser(
           User user
    ) {
        try {
            ps = conn.prepareStatement(INSERT_USER);
            ps.setString(1,user.getLogin());
            ps.setString(2,hashString(user.getPassword()));
            ps.setString(3,user.getName());
            ps.setInt(4,user.getAge());
            ps.setString(5,user.getGender());
            ps.setString(6,user.getAddress());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            DbUtils.closeQuietly(ps);
            return false;
        }
    }

    public boolean updatePasswordForUser(String login) {
        if (checkUserByLogin(login)) {
            try {
                ps = conn.prepareStatement("select password from users where login = ?");
                ps.setString(1,login);
                rs = ps.executeQuery();
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
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(rs);
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
                DbUtils.closeQuietly(ps);
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
}
