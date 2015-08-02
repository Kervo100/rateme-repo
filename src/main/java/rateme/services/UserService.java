package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.User;

import java.util.List;

public class UserService extends Service {
    public UserService() {}

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
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                    id = null;
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
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
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                    success = false;
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
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
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                    success = false;
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return success;
    }

    public User getUserByID(Integer id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
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
            criteria.add(Restrictions.eq("username", name));
            if (criteria.list().size() == 0) {
                user = null;
            }
            else {
                user = (User) criteria.list().get(0);
            }

            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
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
            criteria.add(Restrictions.eq("email", email));
            if (criteria.list().size() == 0) {
                user = null;
            }
            else {
                user = (User) criteria.list().get(0);
            }

            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    public List<User> getUserList(){
        List userList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            userList = criteria.list();

            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                }
                catch (Exception re) {
                    System.err.println("Error when trying to rollback transaction:"); // use logging framework here
                    re.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userList;
    }
}
