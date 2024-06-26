package product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import product.model.ProductBean;
import product.model.ProductDao;
import utility.Paging;

@Controller
public class ProductListController {

	private final String command = "/list.prd";
	private final String getPage = "productList";
 
	@Autowired
	ProductDao productDao; 

	@RequestMapping(command)
	public String list(@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request,
			Model model) {

		System.out.println("ProductListController");
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%" + keyword + "%");

		int totalCount = productDao.getTotalCount(map);
		String url = request.getContextPath() + this.command;

		Paging pageInfo = new Paging(pageNumber, null, totalCount, url, whatColumn, keyword);

		List<ProductBean> productLists = productDao.getProductList(map, pageInfo);
		model.addAttribute("productLists", productLists);
		model.addAttribute("pageInfo", pageInfo);

		return getPage;
	}
}

