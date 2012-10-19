package com.vcc.smarttags.bo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: tuyuri
 * Date: 10/11/12
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsInfo {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    private String Author;
    private String Avatar;
    private Integer Cat_ID;
    private String Cat_Name;
    private String Content;
    private String CreateDate;
    private String Extension;
    private Integer Id;
    private String Image;
    private Boolean IsFlipEnabled;
    private Long NewsID;
    private String NewsUrl;
    private Integer News_mode;
    private String PublishDate;
    private String Sapo;
    private Integer Source;
    private String Subtitle;
    private String Title;
    private Boolean isFocus;
    
    public NewsInfo(Integer cat_ID, String cat_Name, String content,
			String createDate, Integer id, Long newsID, String newsUrl,
			String publishDate, String sapo, Integer source, String title) {
		super();
		Cat_ID = cat_ID;
		Cat_Name = cat_Name;
		Content = content;
		CreateDate = createDate;
		Id = id;
		NewsID = newsID;
		NewsUrl = newsUrl;
		PublishDate = publishDate;
		Sapo = sapo;
		Source = source;
		Title = title;
	}

	public NewsInfo() {
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public Integer getCat_ID() {
        return Cat_ID;
    }

    public void setCat_ID(Integer cat_ID) {
        Cat_ID = cat_ID;
    }

    public String getCat_Name() {
        return Cat_Name;
    }

    public void setCat_Name(String cat_Name) {
        Cat_Name = cat_Name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Boolean getIsFlipEnabled() {
        return IsFlipEnabled;
    }

    public void setIsFlipEnabled(Boolean isFlipEnabled) {
        IsFlipEnabled = isFlipEnabled;
    }

    public Long getNewsID() {
        return NewsID;
    }

    public void setNewsID(Long newsID) {
        NewsID = newsID;
    }

    public String getNewsUrl() {
        return NewsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        NewsUrl = newsUrl;
    }

    public Integer getNews_mode() {
        return News_mode;
    }

    public void setNews_mode(Integer news_mode) {
        News_mode = news_mode;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public static void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        NewsInfo.simpleDateFormat = simpleDateFormat;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getSapo() {
        return Sapo;
    }

    public void setSapo(String sapo) {
        Sapo = sapo;
    }

    public Integer getSource() {
        return Source;
    }

    public void setSource(Integer source) {
        Source = source;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Boolean getFocus() {
        return isFocus;
    }

    public void setFocus(Boolean focus) {
        isFocus = focus;
    }

    public Date getPublishDateD() throws Exception {
        if (this.PublishDate != null) {
            return simpleDateFormat.parse(this.PublishDate);
        }
        return null;
    }

    public Date getCreateDateD() throws Exception {
        if (this.CreateDate != null) {
            return simpleDateFormat.parse(this.CreateDate);
        }
        return null;
    }
}

