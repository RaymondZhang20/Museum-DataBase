package model;

public class ExhibitionHall {
    private Integer Zid;
    private String floor;
    private boolean isOpen;
    private String Zname;
    private Integer Mid;

    public ExhibitionHall (Integer Zid, String floor, boolean isOpen, String Zname, Integer Mid){
        this.Zid = Zid;
        this.floor = floor;
        this.isOpen = isOpen;
        this.Zname = Zname;
        this.Mid = Mid;
    }

    public Integer getZid() {
        return Zid;
    }

    public String getFloor() {
        return floor;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getZname() {
        return Zname;
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

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setZname(String zname) {
        Zname = zname;
    }

    public void setMid(Integer mid) {
        Mid = mid;
    }
}
