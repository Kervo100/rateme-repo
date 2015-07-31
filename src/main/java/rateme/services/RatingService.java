package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Medium;
import rateme.entity.Rating;

import java.util.List;

public class RatingService extends Service {
    public RatingService() {}

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

    public byte getAverageRatingByMediumId(Medium medium) {
        byte averageRating = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Rating.class);
            criteria.add(Restrictions.eq("medium", medium));
            if (criteria.list().size() != 0) {
                List ratingList = criteria.list();
                double value = 0;
                for(int i = 0; i < ratingList.size(); i++) {
                    Rating r = (Rating) ratingList.get(i);
                    value += r.getValue();
                }
                value /= ratingList.size();
                averageRating = (byte) Math.round(value);
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
        return averageRating;
    }
}
