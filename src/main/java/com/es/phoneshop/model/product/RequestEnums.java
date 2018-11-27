package com.es.phoneshop.model.product;

public enum RequestEnums {
    SORTING_PARAMETER("sortingParameter"),
    SEARCH_LINE("searchLine"),
    SEARCH_LINE_ATTRIBUTE("searchLineAttrib"),

    UP_DESCRIPTION("upDescription"),
    DOWN_DESCRIPTION("downDescription"),
    UP_PRICE("upPrice"),
    DOWN_PRICE("downPrice");

    private final String value;

    RequestEnums(String value) {
        this.value = value;
    }

    public String getValue() {return this.value;}

    public static RequestEnums getEnum(String enumValue) {
        for (RequestEnums reqEnum : RequestEnums.values()) {
            if (enumValue.equals(reqEnum.getValue())) {
                return reqEnum;
            }
        }
        throw new NullPointerException();
    }
}
