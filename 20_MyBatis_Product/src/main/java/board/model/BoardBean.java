package board.model;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class BoardBean {
	
	private int num;
	
	@NotEmpty(message = "작성자 입력 누락")
	private String writer;
	
	@NotEmpty(message = "이메일 입력 누락")
	private String email;
	
	@NotEmpty(message = "제목 입력 누락")
	private String subject;
	
	@NotEmpty(message = "비밀번호 입력 누락")
	private String passwd;
	
	private Timestamp reg_date;
	private int readcount;
	private int ref;
	private int re_step;
	private int re_level;
	
	@NotEmpty(message = "내용 입력 누락")
	private String content;
	
	private String ip;
	public BoardBean(int num, String writer, String email, String subject, String passwd, Timestamp reg_date, int ref,
			int re_step, int re_level, String content, String ip) {
		// TODO Auto-generated constructor stub
		super();
		this.num = num;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.passwd = passwd;
		this.reg_date = reg_date;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
		this.content = content;
		this.ip = ip;
	}
	public BoardBean() {
		// TODO Auto-generated constructor stub
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
