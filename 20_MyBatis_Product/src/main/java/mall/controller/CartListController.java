package mall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;
import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class CartListController {
	
	// detailview.jsp(주문) => cartaddcontroller => 로그인성공 => /list.mall요청 => cartlistcontroller
	private final String command = "/list.mall";
	private final String getPage = "mallList";
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(command)
	public String list(HttpSession session) {
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> maplists = mycart.getAllOrderLists();
		Set<Integer> key_set = maplists.keySet();
		List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();
		
		for(Integer pnum : key_set) {
			Integer qty = maplists.get(pnum);
			ProductBean pb = productDao.detailViewByNum(pnum);
			ShoppingInfo shopInfo = new ShoppingInfo();
			shopInfo.setPnum(pnum);
			shopInfo.setPname(pb.getName());
			shopInfo.setQty(qty);
			shopInfo.setPrice(pb.getPrice());
			int amount = pb.getPrice() * qty;
			shopInfo.setAmount(amount);
			shoplists.add(shopInfo);
		}
		session.setAttribute("shoplists", shoplists);
		return getPage;
	}
}
