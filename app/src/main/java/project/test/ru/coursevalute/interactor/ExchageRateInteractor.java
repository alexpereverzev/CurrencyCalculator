package project.test.ru.coursevalute.interactor;


import java.util.ArrayList;

import project.test.ru.coursevalute.callback.CallBackRepository;
import project.test.ru.coursevalute.callback.ExchangeRateCallBack;
import project.test.ru.coursevalute.pojo.Currency;
import project.test.ru.coursevalute.repository.ExchangeRateRepository;

public class ExchageRateInteractor extends Interactor<ExchangeRateCallBack> implements CallBackRepository {

    ExchangeRateRepository repository;

    public ExchageRateInteractor() {
        repository = new ExchangeRateRepository();
        repository.setCallBackRepository(this);
    }

    public void getListRate() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.loadList();
            }
        });
    }

    @Override
    public void setListRate(final ArrayList<Currency> data) {
        MainThread.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.setListRate(data);
            }
        });
    }

    public void calculateConvertCurrency(double inputValue, Currency input, Currency output) {
        double coefficient = (Double.parseDouble(input.getValue()) / Double.parseDouble(input.getNominal())) /
                (Double.parseDouble(output.getValue()) / Double.parseDouble(output.getNominal()));
        callback.setResultConvert(inputValue * coefficient);
    }

}

