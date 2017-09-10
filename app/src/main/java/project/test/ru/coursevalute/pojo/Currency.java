package project.test.ru.coursevalute.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root
public class Currency implements Parcelable {

    @Element(name = "NumCode",required=false)
    String numCode;
    @Element(name = "CharCode",required=false)
    String charCode;

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public String  getValue() {
        return value.replace(",",".");
    }

    @Element(name = "Nominal",required=false)
    String nominal;
    @Element(name = "Name",required=false)
    String name;
    @Element(name = "Value",required=false)
    String  value;

    public static Currency createObject(String[] values){
        Currency valute = new Currency();
        valute.name = values[0];
        valute.charCode = values[1];
        valute.nominal = values[2];
        valute.numCode = values[3];
        valute.value = values[4];
        return  valute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.numCode);
        dest.writeString(this.charCode);
        dest.writeString(this.nominal);
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    public Currency() {
    }

    protected Currency(Parcel in) {
        this.numCode = in.readString();
        this.charCode = in.readString();
        this.nominal = in.readString();
        this.name = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<Currency> CREATOR = new Parcelable.Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel source) {
            return new Currency(source);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
