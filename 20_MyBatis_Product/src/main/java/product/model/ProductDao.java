package product.model;

import java.util.*;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component
public class ProductDao {

	private String namespace = "product.model.Product";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public ProductDao() {
		System.out.println("ProductDao 생성자");
	}

	public List<ProductBean> getProductList(Map<String, String> map, Paging pageInfo) {
		List<ProductBean> lists = new ArrayList<ProductBean>();
		System.out.println(map.get("whatColumn"));
		System.out.println(map.get("keyword"));
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		System.out.println(pageInfo.getOffset());
		System.out.println(pageInfo.getLimit());
		lists = sqlSessionTemplate.selectList(namespace + ".getProductList", map, rowBounds);

		System.out.println("lists.size() : " + lists.size());
		return lists;
	}

	public int getTotalCount(Map<String, String> map) {
		int count = -1;
		count = sqlSessionTemplate.selectOne(namespace + ".getTotalCount", map);
		System.out.println("count : " + count);
		return count;
	}// getTotalCount

	public int insertProduct(ProductBean pb) {
		// TODO Auto-generated method stub
		int cnt = -1;

		cnt = sqlSessionTemplate.insert(namespace + ".insertProduct", pb);
		System.out.println("insertProduct cnt : " + cnt);
		return cnt;
	}

	// 3번(,,)
	public ProductBean detailViewByNum(int num) {
		ProductBean pb = null;
		pb = sqlSessionTemplate.selectOne(namespace + ".detailViewByNum", num);
		return pb;
	}//detailViewByNum

	public int deleteProduct(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace + ".deleteProduct", num);
		System.out.println("deleteProduct cnt:"+cnt);
		return cnt;

	}//deleteProduct

	public int updateProduct(ProductBean product) {
		int cnt = -1;

		cnt = sqlSessionTemplate.update(namespace + ".updateProduct", product);

		System.out.println("updateProduct cnt : " + cnt);

		return cnt;
	}//updateProduct

	public int updateStock(int pnum, int qty) {
		int cnt = -1;
		ProductBean pbean = new ProductBean();
		pbean.setNum(pnum);
		pbean.setStock(qty);
		cnt = sqlSessionTemplate.update(namespace + ".updateStock", pbean);
		return cnt;
	}


}
