package activitytext.example.com.gcrosenews.bean;

public class New {
    private String newsTitle;
    private String newUrl;
    private String desc;
    private String imgUrl;

    public New(String newsTitle, String newUrl, String desc, String imgUrl) {
        this.newsTitle = newsTitle;
        this.newUrl = newUrl;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    public New() {
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
