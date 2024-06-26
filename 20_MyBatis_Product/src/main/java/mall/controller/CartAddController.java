package mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.cart.MyCartList;
import product.model.ProductBean;

@Controller
public class CartAddController {
	
	private final String command = "add.mall";
	private final String gotoPage = "redirect:/list.mall";
	
	String whatColumn;
	String keyword;
	// productDetailView에서 주문 클릭했을 때 num, orderqty, pageNumber를 넘겨받음
	@RequestMapping(command)
	public String add(ProductBean pb,
					@RequestParam(value="pageNumber",required=false) int pageNumber,
					HttpSession session) {
					
		System.out.println(this.getClass()+"POST");
		System.out.println("pb.getNum(): "+pb.getNum());
		System.out.println("pb.getOrderqty(): "+pb.getOrderqty());
		System.out.println("pageNumber: "+pageNumber);
		
		if(session.getAttribute("loginInfo") == null) {
			String destination = String.format("redirect:/detail.prd?num=%d&pageNumber=%d&whatColumn=%s&keyword=%s",
                    pb.getNum(), pageNumber, whatColumn, keyword);
			session.setAttribute("destination", destination);
			
			return "redirect:/loginForm.mb";
		}else {
			MyCartList mycart = (MyCartList)session.getAttribute("mycart"); // 장바구니 이름(mycart)
			System.out.println("mycart: "+mycart);
			if(mycart == null) {
				mycart = new MyCartList();
			}
			System.out.println("mycart2: "+mycart);
			mycart.addOrder(pb.getNum(), pb.getOrderqty());
			session.setAttribute("mycart", mycart);
			
			return gotoPage;
		}
		
	}
}
