package model;

public class GiftStore {
    private Integer Zid;
    private String floor;
    private Integer Mid;

    public GiftStore (Integer Zid, String floor, Integer Mid){
        this.Zid = Zid;
        this.floor = floor;
        this.Mid = Mid;
    }

    public Integer getZid() {
        return Zid;
    }

    public String getFloor() {
        return floor;
    }

    public Integer getMid() {
        return Mid;
    }

    public void setZid(Integer zid) {
        Zid = zid;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setMid(Integer mid) {
        Mid = mid;
    }
}
