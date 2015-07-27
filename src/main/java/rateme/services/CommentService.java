package rateme.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.*;

/**
 * Created by Mo on 06.07.2015.
 */
public class CommentService extends Service {
    public CommentService() {

    }

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
        return false;
    }

    @Override
    public boolean deleteObject(Object object) {
        return false;
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
}
