package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Medium;

import java.util.List;

public class MediumService extends Service {
    public MediumService() {

    }

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
        return false;
    }

    @Override
    public boolean deleteObject(Object object) {
        Medium medium = (Medium) object;
        boolean success = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(medium);
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

    public Medium getMediumById(int id) {
        Medium medium = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            medium = (Medium) session.get(Medium.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
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
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return medium;
    }

    public List<Medium> getMediumList(){
        List mediaList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Medium.class);
            mediaList = criteria.list();

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return mediaList;
    }
}
