package com.sandbox.friday.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class Item {

    private String title;
    private String url;
    private BigDecimal price;

}
