package model;

public class Exhibits3 {
    private Integer Eid;
    private String birthplace;
    private String Eyear;
    private String Ename;
    private Integer Mid;
    private Integer Gid;

    public Exhibits3(Integer eid, String birthplace, String eyear, String ename, Integer mid, Integer gid) {
        this.Eid = eid;
        this.birthplace = birthplace;
        this.Eyear = eyear;
        this.Ename = ename;
        this.Mid = mid;
        this.Gid = gid;
    }

    public Integer getEid() {
        return Eid;
    }

    public void setEid(Integer eid) {
        Eid = eid;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getEyear() {
        return Eyear;
    }

    public void setEyear(String eyear) {
        Eyear = eyear;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public Integer getMid() {
        return Mid;
    }

    public void setMid(Integer mid) {
        Mid = mid;
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        Gid = gid;
    }
}
