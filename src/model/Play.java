package model;

public class Play {
    private String Showtime;
    private Integer Fid;
    private Integer Zid;

    public Play(String showtime, Integer fid, Integer zid) {
        Showtime = showtime;
        Fid = fid;
        Zid = zid;
    }

    public String getShowtime() {
        return Showtime;
    }

    public void setShowtime(String showtime) {
        Showtime = showtime;
    }

    public Integer getFid() {
        return Fid;
    }

    public void setFid(Integer fid) {
        Fid = fid;
    }

    public Integer getZid() {
        return Zid;
    }

    public void setZid(Integer zid) {
        Zid = zid;
    }
}
