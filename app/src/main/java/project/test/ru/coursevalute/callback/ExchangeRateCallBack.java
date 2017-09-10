package project.test.ru.coursevalute.callback;


import java.util.ArrayList;

import project.test.ru.coursevalute.pojo.Currency;

public interface ExchangeRateCallBack {

    void setListRate(ArrayList<Currency> data);

    void setResultConvert(double result);
}
