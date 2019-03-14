package cn.edu.hebtu.software.snowcarsh2.bean;

public class Video {
    private int ivPic;
    private String tvMsg;
    private String videoTime;
    private int like;
    private int talk;
    private int videoId;

    public Video(int ivPic, String tvMsg, String videoTime, int like, int talk) {
        this.ivPic = ivPic;
        this.tvMsg = tvMsg;
        this.videoTime = videoTime;
        this.like = like;
        this.talk = talk;
    }

    public int getIvPic() {
        return ivPic;
    }

    public void setIvPic(int ivPic) {
        this.ivPic = ivPic;
    }

    public String getTvMsg() {
        return tvMsg;
    }

    public void setTvMsg(String tvMsg) {
        this.tvMsg = tvMsg;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
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
