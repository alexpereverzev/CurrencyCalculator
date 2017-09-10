package project.test.ru.coursevalute.presenter;


public abstract class Presenter<T> {

    T view;

    public abstract void attach(T view);

    public abstract void detach();

}
