package org.example.model;

import java.text.MessageFormat;

public class CurrencyRate {

    public String Name;

    public String CharCode;

    public double Value;

    public CurrencyRate() {

    }

    public CurrencyRate(String name, String charCode, double value) {
        this.Name = name;
        this.CharCode = charCode;
        this.Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCharCode() {
        return CharCode;
    }

    public void setCharCode(String charCode) {
        this.CharCode = charCode;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        this.Value = value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Страна: {0}\nкод валюты: {1}\nцена валюты: {2}\n",
                Name, CharCode, Value);
    }
}