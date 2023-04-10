package com.iweb.util;

import com.iweb.entity.Product;

import java.util.Comparator;

/**
 * @author 陈郅治
 * @date 2023/4/10  9:15
 **/
public class ProductSaleComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getSaleCount()<o2.getSaleCount()){
            return 1;
        }else if (o1.getSaleCount()==(o2.getSaleCount())){
            return 0;
        } else {
            return -1;
        }
    }
}
