package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Medium;

import java.util.List;

public class MediumService extends Service {
    public MediumService() {}

    @Override
    public Integer createObject(Object object) {
        Medium medium = (Medium) object;
        Integer id = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(medium);
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

        return id;
    }

    @Override
    public boolean updateObject(Object object) {
        return false;
    }

    @Override
    public boolean deleteObject(Object object) {
        Medium medium = (Medium) object;
        boolean success = true;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(medium);
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

        return success;
    }

    public Medium getMediumById(int id) {
        Medium medium = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            medium = (Medium) session.get(Medium.class, id);
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

        return medium;
    }

    public Medium getMediumByName(String name) {
        Medium medium = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Medium.class);
            criteria.add(Restrictions.eq("name", name));
            if (criteria.list().size() == 0) {
                medium = null;
            }
            else {
                medium = (Medium) criteria.list().get(0);
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

        return medium;
    }

    public List<Medium> getMediumList(){
        List mediaList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Medium.class);
            criteria.addOrder(Order.desc("timestamp"));
            mediaList = criteria.list();

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
        return mediaList;
    }
}
