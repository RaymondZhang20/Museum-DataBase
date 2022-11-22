package model;

public class Exhibits2 {
    private String category;
    private Integer Gid;

    public Exhibits2(String category, Integer gid) {
        this.category = category;
        this.Gid = gid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getGid() {
        return Gid;
    }

    public void setGid(Integer gid) {
        Gid = gid;
    }
}
