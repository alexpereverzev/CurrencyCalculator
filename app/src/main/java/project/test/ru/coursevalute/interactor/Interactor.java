package project.test.ru.coursevalute.interactor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Interactor<T> {

    T callback;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    public void init( T callback){
        this.callback = callback;
    }

    public void destroy(){
        callback = null;
    }

}
