package ua.itea.dao.products;

import org.apache.commons.dbutils.DbUtils;
import ua.itea.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductsDao implements ProductsDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public MySqlProductsDao(Connection conn) {
        this.conn = conn;
    }

    final static private String SELECT_ALL = "SELECT * FROM products";
    final static private String SELECT_BY_CATEGORY = "SELECT * FROM products where category = ?";
    final static private String SELECT_BY_CATEGORIES = "SELECT * FROM products where category in (?)";
    final static private String SELECT_BY_ID = "SELECT * FROM products where id = ?";

    @Override
    public Product getProductById(int id) {
        try {
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            ps.executeQuery();
            rs = ps.getResultSet();
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
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(int category) {
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(SELECT_BY_CATEGORY);
            ps.setInt(1, category);
            ps.executeQuery();
            rs = ps.getResultSet();
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
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return products;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try {
            ps = conn.prepareStatement(SELECT_ALL);
            ps.executeQuery();
            rs = ps.getResultSet();
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
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return products;
    }
}
