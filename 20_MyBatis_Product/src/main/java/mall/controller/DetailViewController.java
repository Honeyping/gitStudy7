package mall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mall.cart.ShoppingInfo;
import orderdetail.model.OrderDetailBean;
import orderdetail.model.OrderDetailDao;
import product.model.CompositeDao;
import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class DetailViewController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CompositeDao compositeDao;
	
	private final String command = "/detailView.mall";
	private final String getPage = "shopDetail";
	
	@RequestMapping(command)
	public ModelAndView detail(@RequestParam(value="oid") int oid,
							HttpSession session) {
		
		List<ShoppingInfo> olists = compositeDao.getOrderDetailByOid(oid);
		
		// List<ProductBean> plists = new ArrayList<ProductBean>();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("olists",olists);
		mav.setViewName(getPage);
		return mav;
	}
}
