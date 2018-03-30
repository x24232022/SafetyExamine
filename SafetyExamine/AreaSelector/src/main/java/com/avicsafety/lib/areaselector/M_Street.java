package com.avicsafety.lib.areaselector;

import java.io.Serializable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "t_street")
public class M_Street implements Serializable {
	@Column(name = "id", isId = true)
	private String id;
	@Column(name = "jdmc")
	private String jdmc;
	@Column(name = "parentId")
	private String parentId;
	@Column(name = "sortid")
	private String sortid;
	@Column(name = "fjdmc")
	private String fjdmc;
	@Column(name = "bm")
	private String bm;

	@Column(name = "qyszd")
	private String qyszd;
	@Column(name = "qyszdId")
	private String qyszdId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the jdmc
	 */
	public String getJdmc() {
		return jdmc;
	}

	/**
	 * @param jdmc
	 *            the jdmc to set
	 */
	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the sortid
	 */
	public String getSortid() {
		return sortid;
	}

	/**
	 * @param sortid
	 *            the sortid to set
	 */
	public void setSortid(String sortid) {
		this.sortid = sortid;
	}

	/**
	 * @return fjdmc
	 */
	public String getFjdmc() {
		return fjdmc;
	}

	/**
	 * @param fjdmc
	 *            Ҫ���õ� fjdmc
	 */
	public void setFjdmc(String fjdmc) {
		this.fjdmc = fjdmc;
	}

	/**
	 * @return bm
	 */
	public String getBm() {
		return bm;
	}

	/**
	 * @param bm
	 *            Ҫ���õ� bm
	 */
	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getQyszd() {
		return qyszd;
	}

	public void setQyszd(String qyszd) {
		this.qyszd = qyszd;
	}

	public String getQyszdId() {
		return qyszdId;
	}

	public void setQyszdId(String qyszdId) {
		this.qyszdId = qyszdId;
	}

}
