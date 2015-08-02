package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.*;

import java.util.List;

public class CommentService extends Service {
    public CommentService() {}

    @Override
    public Integer createObject(Object object) {
        Comment comment = (Comment) object;
        Integer id = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(comment);
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
        Comment comment = (Comment) object;
        Boolean result = true;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(comment);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null){
                transaction.rollback();
                result = false;
            }
            throw e;
        }
        finally {
            session.close();
        }

        return result;
    }

    @Override
    public boolean deleteObject(Object object) {
        Comment comment = (Comment) object;
        boolean success = true;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(comment);
            transaction.commit();
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

    public Comment getCommentByID(int id) {
        Comment comment = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            comment = (Comment) session.get(Comment.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return comment;
    }

    public List<Comment> getCommentListByMedium(Medium medium){
        List commentList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Comment.class);
            criteria
                    .add(Restrictions.eq("medium", medium))
                    .addOrder(Order.desc("timestamp"));
            commentList = criteria.list();

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return commentList;
    }
}
