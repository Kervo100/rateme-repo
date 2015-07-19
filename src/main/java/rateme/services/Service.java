package rateme.services;

/**
 * Created by thorben on 04/07/15.
 */
public abstract class Service {
    public Service() {

    }

    public abstract boolean createObject(Object object);
    public abstract boolean updateObject(Object object);
    public abstract boolean deleteObject(Object object);
}
