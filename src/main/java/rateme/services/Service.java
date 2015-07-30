package rateme.services;

public abstract class Service {
    public Service() {}

    public abstract Integer createObject(Object object);
    public abstract boolean updateObject(Object object);
    public abstract boolean deleteObject(Object object);
}
