package project.test.ru.coursevalute.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import project.test.ru.coursevalute.R;
import project.test.ru.coursevalute.adapter.CurrencyChooseAdapter;
import project.test.ru.coursevalute.pojo.Currency;
import project.test.ru.coursevalute.presenter.ExchangeRatePresenter;
import project.test.ru.coursevalute.view.ExchangeRateView;

public class MainActivity extends AppCompatActivity implements ExchangeRateView, AdapterView.OnItemSelectedListener {

    private final static String CURRENCY_LIST = "currency_list";
    private final static String INPUT_VALUE = "input_value";
    private final static String RESULT_VALUE = "result_value";

    ExchangeRatePresenter presenter;
    TextView tvResultValue;
    TextInputEditText etInputValue;
    Spinner spInputCurrency;
    Spinner spOutPutCurrency;
    Button btCalculate;
    CurrencyChooseAdapter mCurrencyInputChooseAdapter;
    CurrencyChooseAdapter mCurrencyOutputChooseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResultValue = findViewById(R.id.tv_result);
        etInputValue = findViewById(R.id.et_value);
        spInputCurrency = findViewById(R.id.sp_input_currency);
        spOutPutCurrency = findViewById(R.id.sp_output_currency);
        btCalculate = findViewById(R.id.bt_calculate);

        mCurrencyInputChooseAdapter = new CurrencyChooseAdapter(this, android.R.layout.simple_expandable_list_item_1);
        mCurrencyOutputChooseAdapter = new CurrencyChooseAdapter(this, android.R.layout.simple_expandable_list_item_1);

        spInputCurrency.setAdapter(mCurrencyInputChooseAdapter);
        spOutPutCurrency.setAdapter(mCurrencyOutputChooseAdapter);

        presenter = new ExchangeRatePresenter();

        if (savedInstanceState != null) {
            tvResultValue.setText(savedInstanceState.getString(RESULT_VALUE, ""));
            etInputValue.setText(savedInstanceState.getString(INPUT_VALUE, ""));
            ArrayList<Currency> data = savedInstanceState.getParcelableArrayList(CURRENCY_LIST);
            if (data != null) {
                setListRate(data);
            }
        }

        etInputValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvResultValue.setVisibility(View.GONE);
                btCalculate.setEnabled(enableButtonConvert());
            }
        });

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.convertValue(Double.parseDouble(etInputValue.getText().toString()),
                        mCurrencyInputChooseAdapter.getItem(spInputCurrency.getSelectedItemPosition()),
                        mCurrencyOutputChooseAdapter.getItem(spOutPutCurrency.getSelectedItemPosition()));
            }
        });

        spInputCurrency.setOnItemSelectedListener(this);
        spOutPutCurrency.setOnItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach(this);
        presenter.getListRate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detach();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(tvResultValue.getText())) {
            outState.putString(RESULT_VALUE, tvResultValue.getText().toString());
        }
        if (!TextUtils.isEmpty(etInputValue.getText())) {
            outState.putString(INPUT_VALUE, etInputValue.getText().toString());
        }
        if (mCurrencyInputChooseAdapter.getCount() != 0) {
            outState.putParcelableArrayList(CURRENCY_LIST, mCurrencyInputChooseAdapter.getData());
        }
    }

    @Override
    public void setListRate(ArrayList<Currency> data) {
        mCurrencyInputChooseAdapter.setData(data);
        mCurrencyOutputChooseAdapter.setData(data);
        mCurrencyInputChooseAdapter.notifyDataSetChanged();
        mCurrencyOutputChooseAdapter.notifyDataSetChanged();
        btCalculate.setEnabled(enableButtonConvert());
    }

    private boolean enableButtonConvert() {
        return etInputValue.getText().length() != 0 && mCurrencyOutputChooseAdapter.getCount() != 0
                && mCurrencyInputChooseAdapter.getCount() != 0
                && spInputCurrency.getSelectedItemPosition() != spOutPutCurrency.getSelectedItemPosition();
    }

    @Override
    public void resultValue(double rate) {
        tvResultValue.setText(getString(R.string.result, rate));
        tvResultValue.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        btCalculate.setEnabled(enableButtonConvert());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
