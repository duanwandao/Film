package com.zjx.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 网站动态更新电影信息实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_info")
public class WebSiteInfo {

	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@ManyToOne
	@JoinColumn(name="filmId")
	private Film film; // 电影
	
	@ManyToOne
	@JoinColumn(name="webSiteId")
	private WebSite webSite; // 网站
	
	@Column(length=1000)
	private String info; // 信息
	
	@Column(length=500)
	private String url; // 具体网址
	
	private Date publishDate; // 发布日期

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
}
