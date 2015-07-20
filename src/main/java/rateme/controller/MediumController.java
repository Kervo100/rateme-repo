package rateme.controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Medium;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mo on 06.07.2015.
 */
public class MediumController extends Controller {
    public MediumController() {

    }

    @Override
    public boolean createObject(Object object) {
        Medium medium = (Medium) object;
        boolean success = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(medium);
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
    public boolean updateObject(Object object) {
        return false;
    }

    @Override
    public boolean deleteObject(Object object) {
        return false;
    }

    public Medium getMediumByID(int id) {
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Medium.class);
            List allMedia = criteria.list();
            Iterator itr = allMedia.iterator();
            while (itr.hasNext()) {
                Medium medium = (Medium) itr.next();
                if(medium.getName().equals((name))) {
                    return medium;
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

        return null;
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
