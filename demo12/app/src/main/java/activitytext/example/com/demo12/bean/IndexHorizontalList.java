package activitytext.example.com.demo12.bean;

public class IndexHorizontalList {
    /*
    * 首页横向列表
    * id        id
    * image     图片
    * introduce 简介
    * title     标题
    * */
    private int id;
    private int image;
    private String introduce;
    private String title;

    public IndexHorizontalList(int id, int image, String introduce, String title) {
        this.id = id;
        this.image = image;
        this.introduce = introduce;
        this.title = title;
    }

    public IndexHorizontalList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
