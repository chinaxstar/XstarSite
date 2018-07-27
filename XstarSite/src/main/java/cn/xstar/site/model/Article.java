package cn.xstar.site.model;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 文章
 * @since 2018/7/27
 * @author xstar
 * 
 */
@Entity
public class Article {
	@Id
	private int id;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 作者ID
	 */
	private int author;
	/**
	 * 创建时间
	 */
	private long createTime;
	/**
	 * 最后修改时间
	 */
	private long lastModifyTime;
	/**
	 * 最后修改人
	 */
	private int lastModifyAuthor;
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public int getLastModifyAuthor() {
		return lastModifyAuthor;
	}
	public void setLastModifyAuthor(int lastModifyAuthor) {
		this.lastModifyAuthor = lastModifyAuthor;
	}
	
	

}
