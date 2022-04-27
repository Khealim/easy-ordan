package com.easyshopper;

import lombok.Getter;

public enum EasyOrdanOverride
{
    OFF,
    buy10("Buy 10"),
    buy25("Buy 25"),
    buy50("Buy 50");

    @Getter
    private final String buytype;

    EasyOrdanOverride()
    {
        this.buytype = null;
    }

    EasyOrdanOverride(String buytype)
    {
        this.buytype = buytype;
    }

    public String getBuytype() {
        return buytype;
    }
}