package com.iweb.entity;



import lombok.Data;

import java.util.Date;
import java.util.List;

import static com.iweb.entity.OrderStatus.*;

/**订单表
 * @author 陈郅治
 */
@Data
public class Order {
    private int id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private User user;
    /**
     * 每一个订单所对应的订单详情集合
     */
    private List<OrderItem> orderItems;

    /**
     * 订单的总计金额
     */
    private float total;

    /**
     * 订单的商品总数量
     */
    private int totalNumber;

    /**
     * 订单状态
     */
    private OrderStatus status;

    public boolean setStatus(String desc){
        switch (desc){
            case "WAIT_PAY":
                status=WAIT_PAY;
                return true;
            case "WAIT_DELIVERY":
                status=WAIT_DELIVERY;
                return true;
            case "WAIT_CONFIRM":
                status=WAIT_CONFIRM;
                return true;
            case "WAIT_REVIEW":
                status=WAIT_REVIEW;
                return true;
            case "FINISH":
                status=FINISH;
                return true;
            case "DELETE":
                status=DELETE;
                return true;
            default:
                return false;
        }
    }
    public String getStatusDesc(){
        String desc = "未知";
        //可以使用Enum枚举类来代替传统的静态常量
        switch (status){
            case WAIT_PAY:
                desc = "待付款";
                break;
            case WAIT_DELIVERY:
                desc = "待发货";
                break;
            case WAIT_CONFIRM:
                desc = "待收货";
                break;
            case WAIT_REVIEW:
                desc = "待评价";
                break;
            case FINISH:
                desc = "完成";
                break;
            case DELETE:
                desc = "删除";
                break;
            default:
                desc = "未知";
        }
        return desc;
    }


}
