package rateme.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Category;

/**
 * Created by Mo on 13.07.2015.
 */
public class CategoryController extends Controller {

    @Override
    public boolean createObject(Object object) {
        return false;
    }

    @Override
    public boolean updateObject(Object object) {
        return false;
    }

    @Override
    public boolean deleteObject(Object object) {
        return false;
    }

    public Category getCategoryById(int id){
        Category category = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            category = (Category) session.get(Category.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return category;
    }
}
