package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Link;
import rateme.entity.Platform;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mo on 06.07.2015.
 */
public class PlatformService extends Service {
    public PlatformService() {

    }

    @Override
    public Integer createObject(Object object) {
        Platform platform = (Platform) object;
        Integer id = null;

        Platform p = this.getPlatformByName(platform.getName());
        if(p == null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                id = (Integer) session.save(platform);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    id = null;
                }
                throw e;
            } finally {
                session.close();
            }
        }
        else {
            id = p.getId();
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

    public Platform getPlatformById(int id) {
        Platform platform = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            platform = (Platform) session.get(Platform.class, id);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }

        return platform;
    }

    public Platform getPlatformByName(String name) {
        Platform platform = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Platform.class);
            List allUsers = criteria.list();
            Iterator itr = allUsers.iterator();
            while (itr.hasNext()) {
                Platform p = (Platform) itr.next();
                if(p.getName().equals((name))) {
                    platform = p;
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

        return platform;
    }

}
