package model;

public class Author2 {
    private Integer Rid;
    private Integer Eid;

    public Author2(Integer rid, Integer eid) {
        this.Rid = rid;
        this.Eid = eid;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer rid) {
        Rid = rid;
    }

    public Integer getEid() {
        return Eid;
    }

    public void setEid(Integer eid) {
        Eid = eid;
    }
}
