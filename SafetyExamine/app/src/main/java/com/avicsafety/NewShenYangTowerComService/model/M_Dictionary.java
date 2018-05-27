package com.avicsafety.NewShenYangTowerComService.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name="t_dictionary")
public class M_Dictionary{
	    @Column(name = "id",isId=true)
	private int id;
	    @Column(name = "name")
	private String name;
	    @Column(name = "text")
	private String text;
	    @Column(name = "value")
	private String value;
	    @Column(name = "parentId")
	private int parentId;
	    @Column(name = "sortId")
	private int sortId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getSortId() {
		return sortId;
	}
	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	@Override
	public String toString() {
		return "M_Dictionary{" +
				"id=" + id +
				", name='" + name + '\'' +
				", text='" + text + '\'' +
				", value='" + value + '\'' +
				", parentId=" + parentId +
				", sortId=" + sortId +
				'}';
	}
}


