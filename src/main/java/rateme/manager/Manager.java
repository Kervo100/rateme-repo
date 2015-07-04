package rateme.manager;

/**
 * Created by thorben on 04/07/15.
 */
public abstract class Manager {
    public Manager() {

    }

    public abstract boolean createObject(Object object);
    public abstract boolean updateObject(Object object);
    public abstract boolean deleteObject(Object object);
}
