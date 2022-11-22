package model;

public class Guide1 {
    public Integer GId;
    public String Gname;
    public String field;

    public Guide1(Integer GId, String gname, String field) {
        this.GId = GId;
        this.Gname = gname;
        this.field = field;
    }

    public Integer getGId() {
        return GId;
    }

    public void setGId(Integer GId) {
        this.GId = GId;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
