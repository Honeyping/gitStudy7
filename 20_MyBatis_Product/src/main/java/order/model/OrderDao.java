package order.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("OrderDao")
public class OrderDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "order.model.Order";

	public int insertData(String id) {
		int cnt;
		cnt = sqlSessionTemplate.insert(namespace+".insertData",id);
		return cnt;
	}

	public int getOidNum() {
		int cnt;
		cnt = sqlSessionTemplate.selectOne(namespace+".getOidNum");
		return cnt;
	}

	public List<OrderBean> getOrderList(String id) {
		List<OrderBean> orderLists = new ArrayList<OrderBean>();
		orderLists = sqlSessionTemplate.selectList(namespace+".getOrderList",id);
		return orderLists;
	}
	
}
