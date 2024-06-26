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
		// mycart session설정 => CartAddController
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> orderLists = mycart.getAllOrderLists();
		System.out.println("주문정보: "+orderLists.size());
		// 3번 2개, 9번 4개, 5번 1개
		Set<Integer> keyset = orderLists.keySet();
		
		orderDao.insertData(member.getId());
		int maxoid = orderDao.getOidNum();
		
		for(int pnum : keyset) {
			// 상품 재고수량 감소 작업
			int qty = orderLists.get(pnum);
			productDao.updateStock(pnum,qty);
			
			OrderDetailBean odb = new OrderDetailBean();
			odb.setOid(maxoid);
			odb.setPnum(pnum);
			odb.setQty(qty);
			
			orderDetailDao.insertData(odb);
		}
		
		// 회원 포인트 점수 추가 작업
		memberDao.updateMpoint(member.getId(),100);
		// 결제 후 장바구니 비우기
		session.removeAttribute("mycart");
		
		return gotoPage;
	}
}
