package vo;

public class Member {

	private String memberId;
	private String memberPw;
	private String name;
	private String gender;
	private int age;
	private int level;
	private String createDate;
	private String updateDate;
	//constructor
	public Member() {}
	public Member(String memberId, String memberPw, String name, String gender, int age, int level, String createDate,
			String updateDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.level = level;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	//tostring
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + ", level=" + level + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
	//gettersetter
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	

	
	
}
