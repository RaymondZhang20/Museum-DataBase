package model;

public class Film {
    private Integer Fid;
    private Integer Ftime;
    private String Fname;

    public Film(Integer fid, Integer ftime, String fname) {
        Fid = fid;
        Ftime = ftime;
        Fname = fname;
    }

    public Integer getFid() {
        return Fid;
    }

    public void setFid(Integer fid) {
        Fid = fid;
    }

    public Integer getFtime() {
        return Ftime;
    }

    public void setFtime(Integer ftime) {
        Ftime = ftime;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }
}
