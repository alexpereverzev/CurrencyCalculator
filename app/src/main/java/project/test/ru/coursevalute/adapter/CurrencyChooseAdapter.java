package project.test.ru.coursevalute.adapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.test.ru.coursevalute.pojo.Currency;

public class CurrencyChooseAdapter extends ArrayAdapter<Currency> {

    ArrayList<Currency> data = new ArrayList<>();
    LayoutInflater inflater;

    public CurrencyChooseAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public Currency getItem(int position) {
        return data.get(position);
    }

    public void setData(ArrayList<Currency> data) {
        this.data = data;
    }

    public ArrayList<Currency> getData() {
        return data;
    }

    private View getCustomView(int position, ViewGroup parent) {
        View row = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        TextView label = row.findViewById(android.R.id.text1);
        label.setText(getItem(position).getName());
        return row;
    }
}
