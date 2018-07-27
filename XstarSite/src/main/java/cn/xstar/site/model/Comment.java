package cn.xstar.site.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id
	private int id;
	/**
	 * 评论
	 */
	private String content;
	/**
	 * 作者
	 */
	private int authorId;
	/**
	 * 评论文章
	 */
	private int articleId;
	/**
	 * 评论的评论
	 */
	private int commentId;
	/**
	 * 创建时间
	 */
	private long createTime;
	/**
	 * 最后修改时间
	 */
	private long lastModifyTime;
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
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	
	
}
