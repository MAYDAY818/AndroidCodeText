package activitytext.example.com.demo15.bean;

public class Model {
    public static final int FIRST_TYPE = 1;
    public static final int SECOND_TYPE = 2;
    public static final int NORMAL_TYPE = 3;

    //添加类型变量


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Model() {
    }

    public int type;

    public Model(int type, String title, String content, String imgUrl) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    private String title;
    private String content;
    private String imgUrl;

}
