package member.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MemberBean {
	
	private final String message = " 입력 필수";
	
	@NotEmpty(message="아이디"+message)
	private String id;
	
	@NotEmpty(message="이름"+message)
	private String name;
	
	@NotEmpty(message="비밀번호"+message)
	private String password;
	
	private int salary;
	private String hiredate;
	
	@NotNull(message="성별 선택 필수")
	private String gender; // radio
	
	@NotNull(message="취미는 최소 1개 이상 선택")
	private String hobby;
	
	private String job;
	private String zipcode;
	
	@NotEmpty(message="주소1"+message)
	private String address1;
	
	private String address2;
	private int mpoint;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	
}
