package mall.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.cart.MyCartList;
import member.model.MemberBean;
import member.model.MemberDao;
import order.model.OrderDao;
import orderdetail.model.OrderDetailBean;
import orderdetail.model.OrderDetailDao;
import product.model.ProductDao;

@Controller
public class CartCalculateController {
	
	private final String command = "/calculate.mall";
	private final String gotoPage = "redirect:/list.prd";
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@RequestMapping(command)
	public String calculate(
							HttpSession session) {
		
		MemberBean member = (MemberBean)session.getAttribute("loginInfo");
		// mycart session���� => CartAddController
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> orderLists = mycart.getAllOrderLists();
		System.out.println("�ֹ�����: "+orderLists.size());
		// 3�� 2��, 9�� 4��, 5�� 1��
		Set<Integer> keyset = orderLists.keySet();
		
		orderDao.insertData(member.getId());
		int maxoid = orderDao.getOidNum();
		
		for(int pnum : keyset) {
			// ��ǰ ������ ���� �۾�
			int qty = orderLists.get(pnum);
			productDao.updateStock(pnum,qty);
			
			OrderDetailBean odb = new OrderDetailBean();
			odb.setOid(maxoid);
			odb.setPnum(pnum);
			odb.setQty(qty);
			
			orderDetailDao.insertData(odb);
		}
		
		// ȸ�� ����Ʈ ���� �߰� �۾�
		memberDao.updateMpoint(member.getId(),100);
		// ���� �� ��ٱ��� ����
		session.removeAttribute("mycart");
		
		return gotoPage;
	}
}
