package activitytext.example.com.demo07;

import android.media.Image;

//banner
public class Info {
    //种类
    private String bannerCategory;
    //标题
    private String bannerTitle;
    //简要概述
    private String bannerSummary;
    //图片地址
    private String bannerImageUrl;
    //文章地址
    private String bannerUrl;

    public String getBannerCategory() {
        return bannerCategory;
    }

    public void setBannerCategory(String bannerCategory) {
        this.bannerCategory = bannerCategory;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerSummary() {
        return bannerSummary;
    }

    public void setBannerSummary(String bannerSummary) {
        this.bannerSummary = bannerSummary;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public Info(String bannerCategory, String bannerTitle, String bannerSummary, String bannerImageUrl, String bannerUrl) {
        this.bannerCategory = bannerCategory;
        this.bannerTitle = bannerTitle;
        this.bannerSummary = bannerSummary;
        this.bannerImageUrl = bannerImageUrl;
        this.bannerUrl = bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;

    }
}
