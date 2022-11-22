package model;

public class Activity {
    private Integer AID;
    private String Title;
    private String Adate;
    private Integer ZID;

    public Activity(Integer AID, String Title, String Adate, Integer ZID) {
        this.AID = AID;
        this.Title = Title;
        this.Adate = Adate;
        this.ZID = ZID;
    }

    public Integer getAID() {
        return AID;
    }


    public String getAdate() {
        return Adate;
    }

    public String getTitle() {
        return Title;
    }
    public Integer getZID() {
        return ZID;
    }
    public void setAID(Integer AID) {
        this.AID = AID;
    }

    public void setAdate(String adate) {
        Adate = adate;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setZID(Integer ZID) {
        this.ZID = ZID;
    }
}
