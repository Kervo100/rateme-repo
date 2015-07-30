package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Medium;
import rateme.entity.User;

import java.util.Iterator;
import java.util.List;

/**
 * Created by thorben on 04/07/15.
 */

public class UserService extends Service {
    public UserService() {

    }

    @Override
    public Integer createObject(Object object) {
        User user = (User) object;
        Integer id = null;



        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                id = null;
            }
            throw e;
        }
        finally {
            session.close();
        }

        return id;
    }

    @Override
    public boolean updateObject(Object object) {
        User user = (User) object;
        boolean success = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            success = true;
        }
        catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                success = false;
            }
            throw e;
        }
        finally {
            session.close();
        }

        return success;
    }

    @Override
    public boolean deleteObject(Object object) {
        User user = (User) object;
        boolean success = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            success = true;
        }
        catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                success = false;
            }
            throw e;
        }
        finally {
            session.close();
        }

        return success;
    }

    public User getUserByID(int id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return user;
    }

    public User getUserByName(String name) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            List allUsers = criteria.list();
            Iterator itr = allUsers.iterator();
            while (itr.hasNext()) {
                User u = (User) itr.next();
                if(u.getUsername().equals((name))) {
                    user = u;
                }
            }

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return user;
    }

    public User getUserByEmail(String email) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            List allUsers = criteria.list();
            Iterator itr = allUsers.iterator();
            while (itr.hasNext()) {
                User u = (User) itr.next();
                if(u.getEmail().equals((email))) {
                    user = u;
                }
            }

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return user;
    }

    public List<User> getUserList(){
        List userList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            userList = criteria.list();

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return userList;
    }
}
