package project.test.ru.coursevalute.cache;


import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import project.test.ru.coursevalute.App;
import project.test.ru.coursevalute.pojo.Currency;

public class CacheManager {
    private static final String VALUE = "value";
    SharedPreferences preferences = App.getSharedPreference();

    public void saveCache(List<Currency> data){
      StringBuilder valueBuilder = new StringBuilder();
       for (Currency valute : data){
           valueBuilder.append(valute.getName());
           valueBuilder.append(",");
           valueBuilder.append(valute.getCharCode());
           valueBuilder.append(",");
           valueBuilder.append(valute.getNominal());
           valueBuilder.append(",");
           valueBuilder.append(valute.getNumCode());
           valueBuilder.append(",");
           valueBuilder.append(valute.getValue());
           valueBuilder.append(";");
       }
        valueBuilder.setLength(valueBuilder.length() - 1);
       preferences.edit().putString(VALUE,valueBuilder.toString()).apply();
    }

    public ArrayList<Currency> getCache(){
        String cacheValue = preferences.getString(VALUE,"");
        ArrayList<Currency>  resultList = new ArrayList<Currency>();
        if(!TextUtils.isEmpty(cacheValue)) {
          String [] splitResult = cacheValue.split(";");
            for(String valueObjectString : splitResult){
                resultList.add(Currency.createObject(valueObjectString.split(",")));
            }
        }

        return resultList;
    }
}
