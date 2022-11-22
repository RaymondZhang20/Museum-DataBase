package model;

public class Participate {
    private Integer Aid;
    private Integer Gid;

    public Participate(Integer aid, Integer gid) {
        Aid = aid;
        Gid = gid;
    }

    public Integer getAid() {
        return Aid;
    }

    public void setAid(Integer aid) {
        Aid = aid;
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        Gid = gid;
    }
}
