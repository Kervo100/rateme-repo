package rateme.controller;

/**
 * Created by thorben on 04/07/15.
 */
public abstract class Controller {
    public Controller() {

    }

    public abstract boolean createObject(Object object);
    public abstract boolean updateObject(Object object);
    public abstract boolean deleteObject(Object object);
}
