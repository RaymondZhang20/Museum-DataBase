package model;

public class Souvenir {
    private Integer Sid;
    private String Sname;
    private double price;

    public Souvenir(Integer sid, String sname, double price) {
        Sid = sid;
        Sname = sname;
        this.price = price;
    }

    public Integer getSid() {
        return Sid;
    }

    public void setSid(Integer sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
