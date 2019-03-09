package ua.itea.dao.products;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ua.itea.models.Product;
import ua.itea.service.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JpaProductsDao implements ProductsDao {

    private Log log = LogFactory.getLog(getClass());
    EntityManager em = JpaFactory.getEntityManager();

    @Override
    public Product getProductById(int id) {
        try {
            Query query = em.createQuery("SELECT c FROM Product c WHERE c.id = :id",Product.class);
            query.setParameter("id",id);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch  (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Product> getProducts() {
        try {
            Query query = em.createQuery("SELECT c FROM Product c",Product.class);
            return (List<Product>) query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        } catch  (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(int category) {
        try {
            Query query = em.createQuery("SELECT c FROM Product c WHERE c.category = :category",Product.class);
            query.setParameter("category",category);
            return (List<Product>) query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        } catch  (Exception e) {
            log.error(e);
        }
        return Collections.emptyList();
    }
}
