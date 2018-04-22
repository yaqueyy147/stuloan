package com.stuloan.web.mybatis.domain;

public class Userresource {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userresource.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userresource.userid
     *
     * @mbggenerated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userresource.resourceid
     *
     * @mbggenerated
     */
    private String resourceid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column userresource.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userresource
     *
     * @mbggenerated
     */
    public Userresource(String id, String userid, String resourceid, String remark) {
        this.id = id;
        this.userid = userid;
        this.resourceid = resourceid;
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table userresource
     *
     * @mbggenerated
     */
    public Userresource() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userresource.id
     *
     * @return the value of userresource.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userresource.id
     *
     * @param id the value for userresource.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userresource.userid
     *
     * @return the value of userresource.userid
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userresource.userid
     *
     * @param userid the value for userresource.userid
     *
     * @mbggenerated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userresource.resourceid
     *
     * @return the value of userresource.resourceid
     *
     * @mbggenerated
     */
    public String getResourceid() {
        return resourceid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userresource.resourceid
     *
     * @param resourceid the value for userresource.resourceid
     *
     * @mbggenerated
     */
    public void setResourceid(String resourceid) {
        this.resourceid = resourceid == null ? null : resourceid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column userresource.remark
     *
     * @return the value of userresource.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column userresource.remark
     *
     * @param remark the value for userresource.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}