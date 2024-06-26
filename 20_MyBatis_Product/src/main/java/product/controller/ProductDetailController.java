package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductDetailController {

	@Autowired
	ProductDao productDao; 

	private final String command = "/detail.prd";
	private final String getPage = "productDetailView";

	@RequestMapping(value = command)
	public String detailView(
			@RequestParam(value =  "num", required = true) int num,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam(value="whatColumn",required=false) String whatColumn,
			@RequestParam(value="keyword",required=false) String keyword,
			Model model) {

		System.out.println("ProductDetailView");
		ProductBean pb =  productDao.detailViewByNum(num);
		model.addAttribute("product", pb);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("whatColumn", whatColumn);
		model.addAttribute("keyword", keyword);

		return getPage;
	}
}
