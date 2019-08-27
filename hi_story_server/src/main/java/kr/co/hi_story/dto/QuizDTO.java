package kr.co.hi_story.dto;

public class QuizDTO {
	private String uid; 
	private int qid;
    private String dname;
    private String qname;
    private String qcontent1; 
    private String qcontent2;
    private String qcontent3;
    private String qcontent4;
    private String qcontent5;
    private String rname;
    private String cityname;
   
	private String hcontent;
    private String codenum;
    private String solution;
    private String answer;
    
    
    public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public String getQcontent1() {
		return qcontent1;
	}
	public void setQcontent1(String qcontent1) {
		this.qcontent1 = qcontent1;
	}
	public String getQcontent2() {
		return qcontent2;
	}
	public void setQcontent2(String qcontent2) {
		this.qcontent2 = qcontent2;
	}
	public String getQcontent3() {
		return qcontent3;
	}
	public void setQcontent3(String qcontent3) {
		this.qcontent3 = qcontent3;
	}
	public String getQcontent4() {
		return qcontent4;
	}
	public void setQcontent4(String qcontent4) {
		this.qcontent4 = qcontent4;
	}
	public String getQcontent5() {
		return qcontent5;
	}
	public void setQcontent5(String qcontent5) {
		this.qcontent5 = qcontent5;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getHcontent() {
		return hcontent;
	}
	public void setHcontent(String hcontent) {
		this.hcontent = hcontent;
	}
	public String getCodenum() {
		return codenum;
	}
	public void setCodenum(String codenum) {
		this.codenum = codenum;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
}
