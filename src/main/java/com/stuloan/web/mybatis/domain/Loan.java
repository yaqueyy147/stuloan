package com.stuloan.web.mybatis.domain;

import java.util.Date;

public class Loan {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.userid
     *
     * @mbggenerated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.loanamount
     *
     * @mbggenerated
     */
    private Double loanamount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.isstage
     *
     * @mbggenerated
     */
    private String isstage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.repayreal
     *
     * @mbggenerated
     */
    private Double repayreal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.stagenum
     *
     * @mbggenerated
     */
    private Integer stagenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.repayyet
     *
     * @mbggenerated
     */
    private Double repayyet;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.stagenumyet
     *
     * @mbggenerated
     */
    private Integer stagenumyet;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.createdate
     *
     * @mbggenerated
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.updatedate
     *
     * @mbggenerated
     */
    private Date updatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.state
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.auditid
     *
     * @mbggenerated
     */
    private String auditid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.auditman
     *
     * @mbggenerated
     */
    private String auditman;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.auditdate
     *
     * @mbggenerated
     */
    private Date auditdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.auditmsg
     *
     * @mbggenerated
     */
    private String auditmsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.loanpurpose
     *
     * @mbggenerated
     */
    private String loanpurpose;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column loan.loanage
     *
     * @mbggenerated
     */
    private Integer loanage;

    private String ispayoff;

    private String orderno;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan
     *
     * @mbggenerated
     */
    public Loan(String id, String userid, Double loanamount, String isstage, Double repayreal, Integer stagenum
            , Double repayyet, Integer stagenumyet, Date createdate, Date updatedate, String state, String auditid
            , String auditman, Date auditdate, String auditmsg, String remark, String loanpurpose, Integer loanage
            , String ispayoff, String orderno) {
        this.id = id;
        this.userid = userid;
        this.loanamount = loanamount;
        this.isstage = isstage;
        this.repayreal = repayreal;
        this.stagenum = stagenum;
        this.repayyet = repayyet;
        this.stagenumyet = stagenumyet;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.state = state;
        this.auditid = auditid;
        this.auditman = auditman;
        this.auditdate = auditdate;
        this.auditmsg = auditmsg;
        this.remark = remark;
        this.loanpurpose = loanpurpose;
        this.loanage = loanage;
        this.ispayoff = ispayoff;
        this.orderno = orderno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loan
     *
     * @mbggenerated
     */
    public Loan() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.id
     *
     * @return the value of loan.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.id
     *
     * @param id the value for loan.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.userid
     *
     * @return the value of loan.userid
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.userid
     *
     * @param userid the value for loan.userid
     *
     * @mbggenerated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.loanamount
     *
     * @return the value of loan.loanamount
     *
     * @mbggenerated
     */
    public Double getLoanamount() {
        return loanamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.loanamount
     *
     * @param loanamount the value for loan.loanamount
     *
     * @mbggenerated
     */
    public void setLoanamount(Double loanamount) {
        this.loanamount = loanamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.isstage
     *
     * @return the value of loan.isstage
     *
     * @mbggenerated
     */
    public String getIsstage() {
        return isstage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.isstage
     *
     * @param isstage the value for loan.isstage
     *
     * @mbggenerated
     */
    public void setIsstage(String isstage) {
        this.isstage = isstage == null ? null : isstage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.repayreal
     *
     * @return the value of loan.repayreal
     *
     * @mbggenerated
     */
    public Double getRepayreal() {
        return repayreal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.repayreal
     *
     * @param repayreal the value for loan.repayreal
     *
     * @mbggenerated
     */
    public void setRepayreal(Double repayreal) {
        this.repayreal = repayreal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.stagenum
     *
     * @return the value of loan.stagenum
     *
     * @mbggenerated
     */
    public Integer getStagenum() {
        return stagenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.stagenum
     *
     * @param stagenum the value for loan.stagenum
     *
     * @mbggenerated
     */
    public void setStagenum(Integer stagenum) {
        this.stagenum = stagenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.repayyet
     *
     * @return the value of loan.repayyet
     *
     * @mbggenerated
     */
    public Double getRepayyet() {
        return repayyet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.repayyet
     *
     * @param repayyet the value for loan.repayyet
     *
     * @mbggenerated
     */
    public void setRepayyet(Double repayyet) {
        this.repayyet = repayyet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.stagenumyet
     *
     * @return the value of loan.stagenumyet
     *
     * @mbggenerated
     */
    public Integer getStagenumyet() {
        return stagenumyet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.stagenumyet
     *
     * @param stagenumyet the value for loan.stagenumyet
     *
     * @mbggenerated
     */
    public void setStagenumyet(Integer stagenumyet) {
        this.stagenumyet = stagenumyet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.createdate
     *
     * @return the value of loan.createdate
     *
     * @mbggenerated
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.createdate
     *
     * @param createdate the value for loan.createdate
     *
     * @mbggenerated
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.updatedate
     *
     * @return the value of loan.updatedate
     *
     * @mbggenerated
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.updatedate
     *
     * @param updatedate the value for loan.updatedate
     *
     * @mbggenerated
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.state
     *
     * @return the value of loan.state
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.state
     *
     * @param state the value for loan.state
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.auditid
     *
     * @return the value of loan.auditid
     *
     * @mbggenerated
     */
    public String getAuditid() {
        return auditid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.auditid
     *
     * @param auditid the value for loan.auditid
     *
     * @mbggenerated
     */
    public void setAuditid(String auditid) {
        this.auditid = auditid == null ? null : auditid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.auditman
     *
     * @return the value of loan.auditman
     *
     * @mbggenerated
     */
    public String getAuditman() {
        return auditman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.auditman
     *
     * @param auditman the value for loan.auditman
     *
     * @mbggenerated
     */
    public void setAuditman(String auditman) {
        this.auditman = auditman == null ? null : auditman.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.auditdate
     *
     * @return the value of loan.auditdate
     *
     * @mbggenerated
     */
    public Date getAuditdate() {
        return auditdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.auditdate
     *
     * @param auditdate the value for loan.auditdate
     *
     * @mbggenerated
     */
    public void setAuditdate(Date auditdate) {
        this.auditdate = auditdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.auditmsg
     *
     * @return the value of loan.auditmsg
     *
     * @mbggenerated
     */
    public String getAuditmsg() {
        return auditmsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.auditmsg
     *
     * @param auditmsg the value for loan.auditmsg
     *
     * @mbggenerated
     */
    public void setAuditmsg(String auditmsg) {
        this.auditmsg = auditmsg == null ? null : auditmsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.remark
     *
     * @return the value of loan.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.remark
     *
     * @param remark the value for loan.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.loanpurpose
     *
     * @return the value of loan.loanpurpose
     *
     * @mbggenerated
     */
    public String getLoanpurpose() {
        return loanpurpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.loanpurpose
     *
     * @param loanpurpose the value for loan.loanpurpose
     *
     * @mbggenerated
     */
    public void setLoanpurpose(String loanpurpose) {
        this.loanpurpose = loanpurpose == null ? null : loanpurpose.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column loan.loanage
     *
     * @return the value of loan.loanage
     *
     * @mbggenerated
     */
    public Integer getLoanage() {
        return loanage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column loan.loanage
     *
     * @param loanage the value for loan.loanage
     *
     * @mbggenerated
     */
    public void setLoanage(Integer loanage) {
        this.loanage = loanage;
    }

    public String getIspayoff() {
        return ispayoff;
    }

    public void setIspayoff(String ispayoff) {
        this.ispayoff = ispayoff;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
}