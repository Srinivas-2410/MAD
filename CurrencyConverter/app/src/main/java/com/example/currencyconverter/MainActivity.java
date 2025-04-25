package com.example.currencyconverter;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFrom, spinnerTo;
    private EditText editAmount;
    private TextView textResult;

    // Hardcoded exchange rates (Base: USD)
    private final Map<String, Double> exchangeRates = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editAmount = findViewById(R.id.editAmount);
        textResult = findViewById(R.id.textResult);
        Button btnConvert = findViewById(R.id.btnConvert);

        // Initialize Exchange Rates
        setupExchangeRates();

        // Set up Spinners with Currency Options
        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currencies);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Handle Convert Button Click
        btnConvert.setOnClickListener(v -> convertCurrency());
    }

    private void setupExchangeRates() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("GBP", 0.78);
        exchangeRates.put("INR", 82.5);
        exchangeRates.put("JPY", 132.0);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void convertCurrency() {
        String fromCurrency = spinnerFrom.getSelectedItem().toString();
        String toCurrency = spinnerTo.getSelectedItem().toString();
        String amountStr = editAmount.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_amount), Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Safely get exchange rates to avoid NullPointerException
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 1.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 1.0);

        // Perform conversion
        double convertedAmount = (amount / fromRate) * toRate;

        // Format the result correctly with proper locale
        String resultText = getString(R.string.converted_amount, String.format(Locale.US, "%.2f", convertedAmount), toCurrency);
        textResult.setText(resultText);
    }
}
