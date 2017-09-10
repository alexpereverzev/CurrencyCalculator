package project.test.ru.coursevalute.presenter;


import java.util.ArrayList;

import project.test.ru.coursevalute.callback.ExchangeRateCallBack;
import project.test.ru.coursevalute.interactor.ExchageRateInteractor;
import project.test.ru.coursevalute.pojo.Currency;
import project.test.ru.coursevalute.view.ExchangeRateView;

public class ExchangeRatePresenter extends Presenter<ExchangeRateView> implements ExchangeRateCallBack {

    ExchageRateInteractor interactor = new ExchageRateInteractor();

    public void getListRate() {
        interactor.getListRate();
    }

    public void convertValue(double inputValue, Currency inputCurrency, Currency outputCurrency) {
        interactor.calculateConvertCurrency(inputValue, inputCurrency, outputCurrency);
    }

    @Override
    public void setListRate(ArrayList<Currency> data) {
        if(view!=null){
            view.setListRate(data);
        }
    }

    @Override
    public void setResultConvert(double result) {
        if(view!=null) {
            view.resultValue(result);
        }
    }

    @Override
    public void attach(ExchangeRateView view) {
        this.view = view;
        interactor.init(this);
    }

    @Override
    public void detach() {
        view = null;
        interactor.destroy();
    }
}
