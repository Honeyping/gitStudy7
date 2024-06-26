package board.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("BoardDao")
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	private String namespace = "board.model.Board";
	
	public int getArticleCount(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".getArticleCount",map);
		return cnt;
	}
	
	public List<BoardBean> getArticle(Map<String, String> map, Paging pageInfo) {
		List<BoardBean> lists = new ArrayList<BoardBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
        lists = sqlSessionTemplate.selectList(namespace + ".getArticle", map, rowBounds);
        return lists;
	}
	
	public int insertArticle(BoardBean bb) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertArticle",bb);
		return cnt;
	}
	
	public int deleterArticle(int num, String passwd) {
		int cnt = -1;
		String pw = sqlSessionTemplate.selectOne(namespace + ".selectPasswd", num);
        if (pw != null && pw.equals(passwd)) {
        	cnt = sqlSessionTemplate.delete(namespace+".deleterArticle",num);
        }else {
            cnt = 0;
        }
		return cnt;
	}
	

	 public int updateReplyStep(int ref, int re_step) {
		int cnt = -1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref", ref);
		map.put("re_step", re_step);
		cnt = sqlSessionTemplate.update(namespace+".updateReplyStep",map);
		return cnt;	
	 }
	
	public int replyArticle(BoardBean bb) {
		int cnt = -1;
        cnt = sqlSessionTemplate.insert(namespace + ".replyArticle", bb);
		return cnt;
	}
	
	public int updateArticle(BoardBean bb) {
		int cnt = -1;
        String pw = sqlSessionTemplate.selectOne(namespace + ".selectPasswd", bb.getNum());
        if (pw != null && pw.equals(bb.getPasswd())) {
            cnt = sqlSessionTemplate.update(namespace + ".updateArticle", bb);
        }else {
            cnt = 0;
        }
        return cnt;
	}
	
	public int updateReadCount(int readcount) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updateReadCount",readcount);
		return cnt;
	}
	
	public BoardBean viewContent(int num) {
		BoardBean bb = new BoardBean();
		bb = sqlSessionTemplate.selectOne(namespace + ".viewContent", num);
		return bb;
	}

	public int getReadCount(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".getReadCount",num);
		return cnt;
	}

	public int updateReplyStep(Map<String, Integer> map) {
		int cnt = -1;
		sqlSessionTemplate.update(namespace + ".updateReplyStep", map);
		return cnt;
	}

	
}
