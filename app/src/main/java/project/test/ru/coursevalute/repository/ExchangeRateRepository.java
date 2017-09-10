package project.test.ru.coursevalute.repository;


import android.text.TextUtils;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import project.test.ru.coursevalute.cache.CacheManager;
import project.test.ru.coursevalute.callback.CallBackRepository;
import project.test.ru.coursevalute.pojo.ExchangeRateData;
import project.test.ru.coursevalute.pojo.Currency;

public class ExchangeRateRepository {
    private final String URL_REST = "http://www.cbr.ru/scripts/XML_daily.asp";
    private CallBackRepository mCallBackRepository;
    private CacheManager cacheManager = new CacheManager();

    public void setCallBackRepository(CallBackRepository callBackRepository) {
        mCallBackRepository = callBackRepository;
    }

    public void loadList() {
        ArrayList<Currency> result = new ArrayList();
        try {
            String xmlData = retrieveFromNetwork();
            if (!TextUtils.isEmpty(xmlData)) {
                Serializer serializer = new Persister();
                Reader reader = new StringReader(xmlData);
                ExchangeRateData osd = serializer.read(ExchangeRateData.class, reader, false);
                cacheManager.saveCache(osd.getValute());
                result = osd.getValute();
            }
        } catch (Exception e) {
            Log.d(ExchangeRateRepository.class.getName(), "error from network");
            result = cacheManager.getCache();
        }

        if (mCallBackRepository != null) {
            mCallBackRepository.setListRate(result);
        }

    }

    private String retrieveFromNetwork() {

        try {
            URL myUrl = new URL(URL_REST);
            HttpURLConnection myUrlCon =
                    (HttpURLConnection) myUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(myUrlCon.getInputStream(), "Windows-1251"));
            String result = "";
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            myUrlCon.disconnect();
            return result;
        } catch (IOException e) {
            Log.w(getClass().getSimpleName(), "Error for URL_REST ", e);
        }
        return null;
    }

}
