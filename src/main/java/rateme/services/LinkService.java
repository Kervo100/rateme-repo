package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import rateme.HibernateUtil;
import rateme.entity.Link;
import rateme.entity.Medium;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mo on 06.07.2015.
 */
public class LinkService extends Service {
    public LinkService() {

    }

    @Override
    public Integer createObject(Object object) {
        Link link = (Link) object;
        Integer id = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            id = (Integer) session.save(link);
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

    public List<Link> getLinkList(){
        List linkList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Link.class);
            linkList = criteria.list();

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction!=null) transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return linkList;
    }

    public Link getLinkByMediumId(Integer mediumId) {
        Link link = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Link.class);
            List allLinks = criteria.list();
            Iterator itr = allLinks.iterator();
            while (itr.hasNext()) {
                Link l = (Link) itr.next();
                if(l.getMedium().getId() == mediumId) {
                    link = l;
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
        return link;
    }

}
