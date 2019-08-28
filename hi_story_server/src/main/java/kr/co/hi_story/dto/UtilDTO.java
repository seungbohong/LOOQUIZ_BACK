package kr.co.hi_story.dto;

public class UtilDTO {
	private int bid; //뱃지 고유 번호	
	private String rname; //뱃지 이름(동일)
	private String img_url; //뱃지 이미지
	private String sname; //store 이름
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
}
