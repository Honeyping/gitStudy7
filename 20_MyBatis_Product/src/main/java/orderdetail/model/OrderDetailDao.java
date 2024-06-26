package orderdetail.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("OrderDetailDao")
public class OrderDetailDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "orderdetail.model.OrderDetail";

	public int insertData(OrderDetailBean odb) {
		int cnt;
		cnt = sqlSessionTemplate.insert(namespace+".insertData",odb);
		return cnt;
		
	}
	
}
