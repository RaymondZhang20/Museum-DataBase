package model;

public class Sell {
    private Integer inventory;
    private Integer Sid;
    private Integer Zid;

    public Sell(Integer inventory, Integer sid, Integer zid) {
        this.inventory = inventory;
        Sid = sid;
        Zid = zid;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSid() {
        return Sid;
    }

    public void setSid(Integer sid) {
        Sid = sid;
    }

    public Integer getZid() {
        return Zid;
    }

    public void setZid(Integer zid) {
        Zid = zid;
    }
}
