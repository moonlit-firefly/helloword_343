package com.iweb.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 陈郅治
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private Integer start = 0;
    private Integer count = 5;
    private Integer last = 0;

    public void calculateLast(int total){
        //假设总数是50  能够被5整除的
        //最后一页就一定是45
        if(total%count==0){
            last = total-count;
        }else {
            last = total - total%count;
        }
    }
}
