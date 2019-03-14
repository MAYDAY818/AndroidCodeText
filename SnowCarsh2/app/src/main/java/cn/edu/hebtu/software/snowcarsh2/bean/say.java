package cn.edu.hebtu.software.snowcarsh2.bean;

public class say {
    private int icon;
    private String name;
    private String date;
    private String Content;
    private int a1,a2,a3;

    public say(int icon, String name, String date, String content, int a1, int a2, int a3) {
        this.icon = icon;
        this.name = name;
        this.date = date;
        Content = content;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public int getA2() {
        return a2;
    }

    public void setA2(int a2) {
        this.a2 = a2;
    }

    public int getA3() {
        return a3;
    }

    public void setA3(int a3) {
        this.a3 = a3;
    }
}
