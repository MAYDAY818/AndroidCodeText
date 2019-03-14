package activitytext.example.com.demo12.bean;

public class IndexVerticalList {

    /*
     * 首页纵向列表
     * id        id
     * image     图片
     * introduce 简介
     * title     标题
     * */
    private String id;
    private String title;
    private String introduce;
    private String imgUrl;

    public IndexVerticalList(String id, String title, String introduce, String imgUrl) {
        this.id = id;
        this.title = title;
        this.introduce = introduce;
        this.imgUrl = imgUrl;
    }

    public IndexVerticalList() {
    }

    public String getId() {
        return id;
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
}
