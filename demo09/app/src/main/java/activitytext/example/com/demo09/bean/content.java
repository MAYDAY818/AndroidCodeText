package activitytext.example.com.demo09.bean;

public class content {
    private int image;
    private String title;
    private String type;

    public content(int image, String title, String type) {
        this.image = image;
        this.title = title;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
