package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Medium;
import rateme.entity.Rating;
import rateme.entity.User;

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
        return averageRating;
    }

    public Boolean getUserHasRatedByMediumAndUserId(Medium medium, String userId) {
        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        User user = new UserService().getUserByID(Integer.parseInt(userId));
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Rating.class);
            criteria
                    .add(Restrictions.eq("medium", medium))
                    .add(Restrictions.eq("user", user));
            if (criteria.list().size() != 0) {
                result = true;
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                    result = false;
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
        return result;
    }
}
