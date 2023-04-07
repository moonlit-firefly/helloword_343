package com.iweb.entity;


/**
 * @author 陈郅治
 */

public enum OrderStatus {
    //待付款
    WAIT_PAY,
    //待发货
    WAIT_DELIVERY,
    //待收货
    WAIT_CONFIRM,
    //待评价
    WAIT_REVIEW,
    //完成
    FINISH,
    //删除
    DELETE
}
