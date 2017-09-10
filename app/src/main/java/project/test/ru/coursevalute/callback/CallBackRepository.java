package project.test.ru.coursevalute.callback;


import java.util.ArrayList;

import project.test.ru.coursevalute.pojo.Currency;

public interface CallBackRepository {

    void setListRate(ArrayList<Currency> data);
}
