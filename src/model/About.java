package model;

public class About {
    private Integer Fid;
    private Integer Eid;

    public About(Integer fid, Integer eid) {
        Fid = fid;
        Eid = eid;
    }

    public Integer getFid() {
        return Fid;
    }

    public void setFid(Integer fid) {
        Fid = fid;
    }

    public Integer getEid() {
        return Eid;
    }

    public void setEid(Integer eid) {
        Eid = eid;
    }
}
