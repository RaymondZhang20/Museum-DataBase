package model;

public class Museum {
    private Integer Mid;
    private String location;
    private String Mname;
    public Museum(Integer Mid, String location, String Mname){
        this.Mid = Mid;
        this.location = location;
        this.Mname = Mname;
    }

    public Integer getMid() {
        return Mid;
    }

    public String getLocation() {
        return location;
    }

    public String getMname() {
        return Mname;
    }

    public void setMid(Integer mid) {
        Mid = mid;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMname(String mname) {
        Mname = mname;
    }
}
