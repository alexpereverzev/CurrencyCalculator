package project.test.ru.coursevalute.view;


import java.util.ArrayList;

import project.test.ru.coursevalute.pojo.Currency;

public interface ExchangeRateView {

    void setListRate(ArrayList<Currency> data);

    void resultValue(double rate);
}
