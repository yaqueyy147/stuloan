package com.base.web.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@Entity
public class Resource {
    private String id;
    private String sourcecode;
    private String sourcename;
    private String sourcedesc;
    private Integer sourcelevel;
    private Integer sourcetype;
    private String sourceurl;
    private Integer parentsourceid;
    private String parentsourcecode;
    private Integer state;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sourcecode")
    public String getSourcecode() {
        return sourcecode;
    }

    public void setSourcecode(String sourcecode) {
        this.sourcecode = sourcecode;
    }

    @Basic
    @Column(name = "sourcename")
    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    @Basic
    @Column(name = "sourcedesc")
    public String getSourcedesc() {
        return sourcedesc;
    }

    public void setSourcedesc(String sourcedesc) {
        this.sourcedesc = sourcedesc;
    }

    @Basic
    @Column(name = "sourcelevel")
    public Integer getSourcelevel() {
        return sourcelevel;
    }

    public void setSourcelevel(Integer sourcelevel) {
        this.sourcelevel = sourcelevel;
    }

    @Basic
    @Column(name = "sourcetype")
    public Integer getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(Integer sourcetype) {
        this.sourcetype = sourcetype;
    }

    @Basic
    @Column(name = "sourceurl")
    public String getSourceurl() {
        return sourceurl;
    }

    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl;
    }

    @Basic
    @Column(name = "parentsourceid")
    public Integer getParentsourceid() {
        return parentsourceid;
    }

    public void setParentsourceid(Integer parentsourceid) {
        this.parentsourceid = parentsourceid;
    }

    @Basic
    @Column(name = "parentsourcecode")
    public String getParentsourcecode() {
        return parentsourcecode;
    }

    public void setParentsourcecode(String parentsourcecode) {
        this.parentsourcecode = parentsourcecode;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource that = (Resource) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sourcecode != null ? !sourcecode.equals(that.sourcecode) : that.sourcecode != null) return false;
        if (sourcename != null ? !sourcename.equals(that.sourcename) : that.sourcename != null) return false;
        if (sourcedesc != null ? !sourcedesc.equals(that.sourcedesc) : that.sourcedesc != null) return false;
        if (sourcelevel != null ? !sourcelevel.equals(that.sourcelevel) : that.sourcelevel != null) return false;
        if (sourcetype != null ? !sourcetype.equals(that.sourcetype) : that.sourcetype != null) return false;
        if (sourceurl != null ? !sourceurl.equals(that.sourceurl) : that.sourceurl != null) return false;
        if (parentsourceid != null ? !parentsourceid.equals(that.parentsourceid) : that.parentsourceid != null)
            return false;
        if (parentsourcecode != null ? !parentsourcecode.equals(that.parentsourcecode) : that.parentsourcecode != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sourcecode != null ? sourcecode.hashCode() : 0);
        result = 31 * result + (sourcename != null ? sourcename.hashCode() : 0);
        result = 31 * result + (sourcedesc != null ? sourcedesc.hashCode() : 0);
        result = 31 * result + (sourcelevel != null ? sourcelevel.hashCode() : 0);
        result = 31 * result + (sourcetype != null ? sourcetype.hashCode() : 0);
        result = 31 * result + (sourceurl != null ? sourceurl.hashCode() : 0);
        result = 31 * result + (parentsourceid != null ? parentsourceid.hashCode() : 0);
        result = 31 * result + (parentsourcecode != null ? parentsourcecode.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
