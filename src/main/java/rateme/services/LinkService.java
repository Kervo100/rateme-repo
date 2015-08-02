package rateme.services;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import rateme.HibernateUtil;
import rateme.entity.Link;
import rateme.entity.Medium;

import java.util.List;

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
        catch (HibernateException e) {
            if (transaction!=null) {
                try {
                    transaction.rollback();
                    id = null;
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

    public List<Link> getLinkList(){
        List linkList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Link.class);
            linkList = criteria.list();

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
        return linkList;
    }

    public Link getLinkByMediumId(Integer mediumId) {
        Medium medium = new MediumService().getMediumById(mediumId);
        Link link = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Link.class);
            criteria.add(Restrictions.eq("medium", medium));
            if (criteria.list().size() == 0) {
                link = null;
            }
            else {
                link = (Link) criteria.list().get(0);
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
        return link;
    }

}
