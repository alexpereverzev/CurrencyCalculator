package project.test.ru.coursevalute.pojo;


import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "ValCurs")
public class ExchangeRateData {

    @Attribute(name = "Date")
    String date = "";
    @Attribute
    String name;

    public ArrayList<Currency> getValute() {
        return Valute;
    }

    @ElementList(required = false, entry="Valute", inline = true)
    ArrayList<Currency> Valute;
}
