package ua.itea.dao.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ua.itea.models.User;
import ua.itea.service.JpaFactory;
import ua.itea.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class JpaUserDao implements UserDao {

    private Log log = LogFactory.getLog(getClass());
    EntityManager em = JpaFactory.getEntityManager();

    @Override
    public boolean checkUserByLogin(String login) {
        try {
            Query query = em.createQuery("SELECT c.login FROM User c WHERE c.login = :login",String.class);
            query.setParameter("login",login);
            String res = (String) query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        } catch  (Exception e) {
            log.error(e);
        }
        return true;
    }

    @Override
    public boolean checkLogin(String login, String password) {
        if (checkUserByLogin(login)) {
            try {
                Query query = em.createQuery("SELECT c.password FROM User c WHERE c.login = :login",String.class);
                query.setParameter("login",login);
                String pass = (String) query.getSingleResult();

                if (Utils.hashString(password).equals(pass)) {
                    return true;
                }
            } catch (NoResultException e) {
                return false;
            } catch  (Exception e) {
                log.error(e);
            }
        }
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        em.getTransaction().begin();
        try {
            user.setPassword(Utils.hashString(user.getPassword()));
            em.merge(user);
        } catch (Exception e) {
            log.error(e);
            em.getTransaction().rollback();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updatePasswordForUser(String login) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        try {
            Query query = em.createQuery("SELECT c FROM User c WHERE c.id = :id");
            query.setParameter("id", id);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            Query query = em.createQuery("SELECT c FROM User c WHERE c.login = :login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }
}
