package org.example;

import org.example.model.CurrencyRate;
import org.json.JSONObject;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.MessageFormat;

public class CurrencyCalculation {

    private String url = ("https://www.cbr-xml-daily.ru/daily_json.js");

    private DecimalFormat df = new DecimalFormat("#.##");

    public CurrencyCalculation() {

    }

    public JSONObject course(String charCode) {
        JSONObject currenciesData = null;
        try {
            String json = new String(
                    new URL(url).openStream().readAllBytes());

            currenciesData = new JSONObject(json).getJSONObject("Valute");

            CurrencyRate currencyRate = new CurrencyRate();

            currencyRate.setCharCode(charCode);
            currencyRate.setName(currenciesData.getJSONObject(charCode).getString("Name"));
            currencyRate.setValue(currenciesData.getJSONObject(charCode).getDouble("Value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currenciesData;
    }

    public String calculations(String buyCurrency, String sellCurrency) {
        if (buyCurrency.equals(sellCurrency)) {
            return MessageFormat.format("Значение {0} и {1} не должны быть одинаковыми.", buyCurrency, sellCurrency);
        } else if (buyCurrency.equals("RUB") || sellCurrency.equals("RUB")) {
            if (buyCurrency.equals("RUB")) {
                DecimalFormat format = new DecimalFormat("#.######");
                double result = course(sellCurrency).getJSONObject(sellCurrency).getDouble("Value");
                double finalResult = 1 / result;
                System.out.println(format.format(finalResult));
                return String.valueOf(format.format(finalResult));
            } else if (sellCurrency.equals("RUB")) {
                double result = course(buyCurrency).getJSONObject(buyCurrency).getDouble("Value");
                System.out.println(df.format(result));
                return String.valueOf(df.format(result));
            }
        } else {
            return String.valueOf(calculateCourse(buyCurrency, sellCurrency));
        }
        return buyCurrency;
    }

    public Double calculateCourse(String buyCurrency, String sellCurrency) {
        JSONObject JSONObject = course(buyCurrency);
        double sell = JSONObject.getJSONObject(sellCurrency).getDouble("Value");
        double buy = JSONObject.getJSONObject(buyCurrency).getDouble("Value");
        double result = sell/buy;
        System.out.println(df.format(result));
        return result;
    }
}
