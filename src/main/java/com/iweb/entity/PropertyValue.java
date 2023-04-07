package com.iweb.entity;

import lombok.Data;

/**
 * @author 陈郅治
 */
@Data
public class PropertyValue {
    private int id;
    private String value;
    private Product product;
    private Property property;

}
