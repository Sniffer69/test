package com.example.KafkaBackendFinal.Producer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userid;
    private String priority;
    private String is_device_check;
    private String user_cat;
    private String user_spam;
    private String is_spam;
    private String is_credit_rev_enable;
    private String is_dnd;
    private String rpi;
    private String alt_dcs;
    private String pid;
    private String dlr_mask;
    private String deferred;
    private String validity;
    private String compress;
    private String coding;
    private String mwi;
    private String mclass;
    private String sms_type;
    private String time;

    public UserDao(String userid, String priority, String is_device_check, String user_cat, String user_spam, String is_spam, String is_credit_rev_enable, String is_dnd, String rpi, String alt_dcs, String pid, String dlr_mask, String deferred, String validity, String compress, String coding, String mwi, String mclass, String sms_type, String time) {
        this.userid = userid;
        this.priority = priority;
        this.is_device_check = is_device_check;
        this.user_cat = user_cat;
        this.user_spam = user_spam;
        this.is_spam = is_spam;
        this.is_credit_rev_enable = is_credit_rev_enable;
        this.is_dnd = is_dnd;
        this.rpi = rpi;
        this.alt_dcs = alt_dcs;
        this.pid = pid;
        this.dlr_mask = dlr_mask;
        this.deferred = deferred;
        this.validity = validity;
        this.compress = compress;
        this.coding = coding;
        this.mwi = mwi;
        this.mclass = mclass;
        this.sms_type = sms_type;
        this.time = time;
    }

    public UserDao() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIs_device_check() {
        return is_device_check;
    }

    public void setIs_device_check(String is_device_check) {
        this.is_device_check = is_device_check;
    }

    public String getUser_cat() {
        return user_cat;
    }

    public void setUser_cat(String user_cat) {
        this.user_cat = user_cat;
    }

    public String getUser_spam() {
        return user_spam;
    }

    public void setUser_spam(String user_spam) {
        this.user_spam = user_spam;
    }

    public String getIs_spam() {
        return is_spam;
    }

    public void setIs_spam(String is_spam) {
        this.is_spam = is_spam;
    }

    public String getIs_credit_rev_enable() {
        return is_credit_rev_enable;
    }

    public void setIs_credit_rev_enable(String is_credit_rev_enable) {
        this.is_credit_rev_enable = is_credit_rev_enable;
    }

    public String getIs_dnd() {
        return is_dnd;
    }

    public void setIs_dnd(String is_dnd) {
        this.is_dnd = is_dnd;
    }

    public String getRpi() {
        return rpi;
    }

    public void setRpi(String rpi) {
        this.rpi = rpi;
    }

    public String getAlt_dcs() {
        return alt_dcs;
    }

    public void setAlt_dcs(String alt_dcs) {
        this.alt_dcs = alt_dcs;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDlr_mask() {
        return dlr_mask;
    }

    public void setDlr_mask(String dlr_mask) {
        this.dlr_mask = dlr_mask;
    }

    public String getDeferred() {
        return deferred;
    }

    public void setDeferred(String deferred) {
        this.deferred = deferred;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getCompress() {
        return compress;
    }

    public void setCompress(String compress) {
        this.compress = compress;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public String getMwi() {
        return mwi;
    }

    public void setMwi(String mwi) {
        this.mwi = mwi;
    }

    public String getMclass() {
        return mclass;
    }

    public void setMclass(String mclass) {
        this.mclass = mclass;
    }

    public String getSms_type() {
        return sms_type;
    }

    public void setSms_type(String sms_type) {
        this.sms_type = sms_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "userid='" + userid + '\'' +
                ", priority='" + priority + '\'' +
                ", is_device_check='" + is_device_check + '\'' +
                ", user_cat='" + user_cat + '\'' +
                ", user_spam='" + user_spam + '\'' +
                ", is_spam='" + is_spam + '\'' +
                ", is_credit_rev_enable='" + is_credit_rev_enable + '\'' +
                ", is_dnd='" + is_dnd + '\'' +
                ", rpi='" + rpi + '\'' +
                ", alt_dcs='" + alt_dcs + '\'' +
                ", pid='" + pid + '\'' +
                ", dlr_mask='" + dlr_mask + '\'' +
                ", deferred='" + deferred + '\'' +
                ", validity='" + validity + '\'' +
                ", compress='" + compress + '\'' +
                ", coding='" + coding + '\'' +
                ", mwi='" + mwi + '\'' +
                ", mclass='" + mclass + '\'' +
                ", sms_type='" + sms_type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
