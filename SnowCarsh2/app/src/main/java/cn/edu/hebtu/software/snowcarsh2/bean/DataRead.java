package cn.edu.hebtu.software.snowcarsh2.bean;

public class DataRead {
    private String title;
    private int pic;
    private int id;
    private int love;
    private int say;

    public DataRead(String title, int pic, int id, int love, int say) {
        this.title = title;
        this.pic = pic;
        this.id = id;
        this.love = love;
        this.say = say;
    }

    public DataRead(String title, int pic, int love, int say) {
        this.title = title;
        this.pic = pic;
        this.love = love;
        this.say = say;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getSay() {
        return say;
    }

    public void setSay(int say) {
        this.say = say;
    }
}
