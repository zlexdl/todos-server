package com.todo.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable {
        private String user_open_id;
        private String user_name;
        private String user_avatar;
        private String user_address;
        private int pay_status;
        private BigDecimal pre_amount;
        private BigDecimal total_amount;

    public String getUser_open_id() {
        return user_open_id;
    }

    public void setUser_open_id(String user_open_id) {
        this.user_open_id = user_open_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public BigDecimal getPre_amount() {
        return pre_amount;
    }

    public void setPre_amount(BigDecimal pre_amount) {
        this.pre_amount = pre_amount;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }
}
