package model;

public class Author1 {
    private Integer Rid;
    private String Uname;
    private String nation;

    public Author1(Integer rid, String uname, String nation) {

        this.Rid = rid;
        this.Uname = uname;
        this.nation = nation;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer rid) {
        Rid = rid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
