package rateme.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Rating;

/**
 * Created by Mo on 06.07.2015.
 */
public class RatingService extends Service {
    public RatingService() {

    }

    @Override
    public Integer createObject(Object object) {
        Rating rating = (Rating) object;
        Integer id = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(rating);
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
        return false;
    }

    public Rating getRatingByID(int id) {
        Rating rating = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            rating = (Rating) session.get(Rating.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return rating;
    }
}
