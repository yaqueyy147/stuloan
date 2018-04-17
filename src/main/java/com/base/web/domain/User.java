package com.base.web.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@Entity
public class User {
    private String id;
    private String username;
    private String loginname;
    private String password;
    private String idcard;
    private String phone;
    private String wechart;
    private String qqnum;
    private String remark;
    private String createman;
    private String createdate;
    private String province;
    private String city;
    private String district;
    private String county;
    private String town;
    private String detailaddr;
    private String userphoto;
    private String idcardphoto;
    private Integer state;
    private Integer isconsole;
    private Integer isfront;
    private Integer userfrom;

    public User(String loginname){
        this.loginname = loginname;
    }

    public User() {
    }

    public User(String loginname, String password){
        this.loginname = loginname;
        this.password = password;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "loginname")
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "idcard")
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "wechart")
    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    @Basic
    @Column(name = "qqnum")
    public String getQqnum() {
        return qqnum;
    }

    public void setQqnum(String qqnum) {
        this.qqnum = qqnum;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "createman")
    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman;
    }

    @Basic
    @Column(name = "createdate")
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "district")
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Basic
    @Column(name = "county")
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "detailaddr")
    public String getDetailaddr() {
        return detailaddr;
    }

    public void setDetailaddr(String detailaddr) {
        this.detailaddr = detailaddr;
    }

    @Basic
    @Column(name = "userphoto")
    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    @Basic
    @Column(name = "idcardphoto")
    public String getIdcardphoto() {
        return idcardphoto;
    }

    public void setIdcardphoto(String idcardphoto) {
        this.idcardphoto = idcardphoto;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "isconsole")
    public Integer getIsconsole() {
        return isconsole;
    }

    public void setIsconsole(Integer isconsole) {
        this.isconsole = isconsole;
    }

    @Basic
    @Column(name = "isfront")
    public Integer getIsfront() {
        return isfront;
    }

    public void setIsfront(Integer isfront) {
        this.isfront = isfront;
    }

    @Basic
    @Column(name = "userfrom")
    public Integer getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(Integer userfrom) {
        this.userfrom = userfrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (loginname != null ? !loginname.equals(that.loginname) : that.loginname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (idcard != null ? !idcard.equals(that.idcard) : that.idcard != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (wechart != null ? !wechart.equals(that.wechart) : that.wechart != null) return false;
        if (qqnum != null ? !qqnum.equals(that.qqnum) : that.qqnum != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createman != null ? !createman.equals(that.createman) : that.createman != null) return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (town != null ? !town.equals(that.town) : that.town != null) return false;
        if (detailaddr != null ? !detailaddr.equals(that.detailaddr) : that.detailaddr != null) return false;
        if (userphoto != null ? !userphoto.equals(that.userphoto) : that.userphoto != null) return false;
        if (idcardphoto != null ? !idcardphoto.equals(that.idcardphoto) : that.idcardphoto != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (isconsole != null ? !isconsole.equals(that.isconsole) : that.isconsole != null) return false;
        if (isfront != null ? !isfront.equals(that.isfront) : that.isfront != null) return false;
        if (userfrom != null ? !userfrom.equals(that.userfrom) : that.userfrom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (loginname != null ? loginname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (wechart != null ? wechart.hashCode() : 0);
        result = 31 * result + (qqnum != null ? qqnum.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createman != null ? createman.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        result = 31 * result + (detailaddr != null ? detailaddr.hashCode() : 0);
        result = 31 * result + (userphoto != null ? userphoto.hashCode() : 0);
        result = 31 * result + (idcardphoto != null ? idcardphoto.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (isconsole != null ? isconsole.hashCode() : 0);
        result = 31 * result + (isfront != null ? isfront.hashCode() : 0);
        result = 31 * result + (userfrom != null ? userfrom.hashCode() : 0);
        return result;
    }
}
