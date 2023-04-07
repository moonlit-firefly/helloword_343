package com.iweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈郅治
 */
@Data
@NoArgsConstructor
public class ProductImage {
    private int id;
    private String url;
    private Product product;
}
