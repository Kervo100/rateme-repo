package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Platform;

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
            criteria.add(Restrictions.eq("name", name));
            platform = (Platform) criteria.list().get(0);

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
