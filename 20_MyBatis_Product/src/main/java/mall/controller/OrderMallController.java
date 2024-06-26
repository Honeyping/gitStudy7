package mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.MemberBean;
import order.model.OrderBean;
import order.model.OrderDao;

@Controller
public class OrderMallController {
	
	private final String command = "/order.mall";
	private final String gotoPage = "redirect:/loginForm.mb";
	private final String getPage = "shopList";
	
	@Autowired
	OrderDao orderDao;
	
	// main.jsp¿¡¼­
	@RequestMapping(command)
	public String mall(HttpSession session,
						Model model) {
		MemberBean mb = (MemberBean)session.getAttribute("loginInfo");
		
		if(session.getAttribute("loginInfo") == null) {
			String destination = String.format("redirect:/order.mall");
			session.setAttribute("destination", destination);
			
			return gotoPage;
			
		}else {
			List<OrderBean> orderLists = orderDao.getOrderList(mb.getId());
			
			for(OrderBean ob : orderLists) {
				System.out.println(ob.getOid());
				System.out.println(ob.getMid());
				System.out.println(ob.getOrderdate());
			}
			
			model.addAttribute("orderLists", orderLists);
			return getPage;
		}
	}
}
