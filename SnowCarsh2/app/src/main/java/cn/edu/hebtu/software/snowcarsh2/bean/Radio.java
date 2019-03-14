package cn.edu.hebtu.software.snowcarsh2.bean;

public class Radio {
    private int imgPic;
    private String tvMsg;
    private int like;
    private int talk;
    private int radioId;

    public Radio(int imgPic, String tvMsg, int like, int talk) {
        this.imgPic = imgPic;
        this.tvMsg = tvMsg;
        this.like = like;
        this.talk = talk;
    }

    public int getImgPic() {
        return imgPic;
    }

    public void setImgPic(int imgPic) {
        this.imgPic = imgPic;
    }

    public String getTvMsg() {
        return tvMsg;
    }

    public void setTvMsg(String tvMsg) {
        this.tvMsg = tvMsg;
    }

    public int getRadioId() {
        return radioId;
    }

    public void setRadioId(int radioId) {
        this.radioId = radioId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getTalk() {
        return talk;
    }

    public void setTalk(int talk) {
        this.talk = talk;
    }
}
