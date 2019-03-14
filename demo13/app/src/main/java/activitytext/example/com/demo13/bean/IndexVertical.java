package activitytext.example.com.demo13.bean;

public class IndexVertical {
    /*
     * 首页纵向列表
     * id        id
     * image     图片
     * introduce 简介
     * title     标题
     * */
    private String id;

    public String getId() {
        return id;
    }

    public IndexVertical() {
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String title;
    private String introduce;

    public IndexVertical(String id, String title, String introduce, String imgUrl) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.imgUrl = imgUrl;
    }

    private String imgUrl;
}
