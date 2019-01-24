package cn.xstar.site.model;

import javax.persistence.*;
import java.util.Date;


/**
 * 文章
 *
 * @author xstar
 * @since 2018/7/27
 */
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 标题
     */
    private String title;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 作者
     */
    @ManyToOne
    private User author;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;
    /**
     * 最后修改人
     */
    @ManyToOne
    private User lastModifyAuthor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public User getLastModifyAuthor() {
        return lastModifyAuthor;
    }

    public void setLastModifyAuthor(User lastModifyAuthor) {
        this.lastModifyAuthor = lastModifyAuthor;
    }
}
