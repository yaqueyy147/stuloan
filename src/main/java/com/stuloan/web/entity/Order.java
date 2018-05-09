package com.stuloan.web.entity;

import java.util.Date;

/**
 * Created by suyx on 2018/4/27 0027.
 */
public class Order {
    private String id;
    private String orderno;
    private String orderqrimage;
    private String orderstate;
    private Date paydate;
    private Date createdate;
    private String orderdesc;
    private String ordertitle;
    private Double totalamount;
    private String timeoutexpress="120m";
    private String sellerid="";
    private String operatorid="test_operator_id";
    private String storeid="test_store_id";
    private String undiscountableamount="0";
    private String remark;

    public Order() {
    }

    public Order(String id) {
        this.id = id;
    }

    public Order(String id, String orderno, String orderqrimage, String orderstate,
                 Date paydate, Date createdate, String orderdesc, String ordertitle,
                 Double totalamount, String timeoutexpress, String sellerid,
                 String operatorid, String storeid, String undiscountableamount,
                 String remark) {
        this.id = id;
        this.orderno = orderno;
        this.orderqrimage = orderqrimage;
        this.orderstate = orderstate;
        this.paydate = paydate;
        this.createdate = createdate;
        this.orderdesc = orderdesc;
        this.ordertitle = ordertitle;
        this.totalamount = totalamount;
        this.timeoutexpress = timeoutexpress;
        this.sellerid = sellerid;
        this.operatorid = operatorid;
        this.storeid = storeid;
        this.undiscountableamount = undiscountableamount;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderqrimage() {
        return orderqrimage;
    }

    public void setOrderqrimage(String orderqrimage) {
        this.orderqrimage = orderqrimage;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getOrderdesc() {
        return orderdesc;
    }

    public void setOrderdesc(String orderdesc) {
        this.orderdesc = orderdesc;
    }

    public String getOrdertitle() {
        return ordertitle;
    }

    public void setOrdertitle(String ordertitle) {
        this.ordertitle = ordertitle;
    }

    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTimeoutexpress() {
        return timeoutexpress;
    }

    public void setTimeoutexpress(String timeoutexpress) {
        this.timeoutexpress = timeoutexpress;
    }

    public String getUndiscountableamount() {
        return undiscountableamount;
    }

    public void setUndiscountableamount(String undiscountableamount) {
        this.undiscountableamount = undiscountableamount;
    }
}
