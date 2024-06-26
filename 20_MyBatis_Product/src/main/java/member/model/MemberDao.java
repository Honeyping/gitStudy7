package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("MemberDao")
public class MemberDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "member.model.Member";
	public List<MemberBean> getMemberList(Map<String,String> map, Paging pageInfo) {
		List<MemberBean> list = new ArrayList<MemberBean>();
		RowBounds rowbounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		list = sqlSessionTemplate.selectList(namespace+".getMemberList", map, rowbounds);
		return list;
	}//getMemberList
	
	public int getTotalCount(Map<String,String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".getTotalCount", map);
		return cnt;
	}//getTotalCount

	public int insertMember(MemberBean member) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertMember",member);
		return cnt;
	}//insertMember

	public int deleteMember(String id) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace+".deleteMember",id);
		return cnt;
	}

	public MemberBean getMember(String id) {
		MemberBean member = null;
		member = sqlSessionTemplate.selectOne(namespace+".getMember",id);
		return member;
	}

	public int updateMember(MemberBean member) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace + ".updateMember",member);
		return cnt;
	}

	public int loginMember(MemberBean member) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".loginMember",member);
		return cnt;
	}

	public int updateMpoint(String id, int point) {
		int cnt = -1;
		MemberBean mbean = new MemberBean();
		mbean.setId(id);
		mbean.setMpoint(point);
		
		cnt = sqlSessionTemplate.update(namespace + ".updateMpoint",mbean);
		return cnt;
	}
	
}