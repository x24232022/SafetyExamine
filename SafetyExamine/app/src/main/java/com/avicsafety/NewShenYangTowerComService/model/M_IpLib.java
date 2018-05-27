package com.avicsafety.NewShenYangTowerComService.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name="t_ip_lib")
public class M_IpLib {
			 
    @Column(name = "id",isId=true)
	private int id;
			
    @Column(name = "ip")
	private String ip;
			
    @Column(name = "name")
	private String name;
			
    @Column(name = "enname")
	private String enname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}
}