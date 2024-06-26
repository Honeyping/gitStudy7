package product.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mall.cart.ShoppingInfo;

@Component("CompositeDao")
public class CompositeDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "composite.model.Composite";

	public List<ShoppingInfo> getOrderDetailByOid(int oid) {
		List<ShoppingInfo> lists = null;
		lists = sqlSessionTemplate.selectList(namespace+".getOrderDetailByOid",oid);
		return lists;
	}
	
}
