package product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import product.model.ProductBean;
import product.model.ProductDao;

@Controller
public class ProductUpdateController {
	private final String command = "update.prd";
	private final String getPage = "productUpdateForm";
	private final String gotoPage = "redirect:/list.prd";
	private final String loginPage = "redirect:/loginForm.mb";
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	ServletContext servletContext;
	
	// productList.jsp에서 수정클릭
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String updateForm(	
			@RequestParam(value="num", required = true) int num,
			@RequestParam(value="pageNumber", required = true) int pageNumber,
			@RequestParam(value="whatColumn", required = false) String whatColumn,
			@RequestParam(value="keyword", required = false) String keyword,
			HttpSession session,Model model) {

		System.out.println(this.getClass()+"GET");
		System.out.println(num);
		System.out.println(pageNumber);
		System.out.println(whatColumn);
		System.out.println(keyword);
		ModelAndView mav = new ModelAndView();
		//		
		//		
		//		ModelAndView mav = new ModelAndView();
		//		mav.addObject("product", pb);
		//		mav.addObject("pageNumber", pageNumber);
		//		mav.addObject("whatColumn", whatColumn);
		//		mav.addObject("keyword", keyword);
		//		mav.setViewName(getPage); // productUpdateForm

		if(session.getAttribute("loginInfo") == null) {
			String destination = String.format("redirect:/update.prd?num=%d&pageNumber=%d&whatColumn=%s&keyword=%s",
                    num, pageNumber, whatColumn, keyword);
			session.setAttribute("destination", destination);
//			mav.addObject("pageNumber", pageNumber);
//			mav.addObject("whatColumn", whatColumn);
//			mav.addObject("keyword", keyword);
			model.addAttribute("whatColumn" , whatColumn);
			model.addAttribute("keyword" , keyword);
			model.addAttribute("pageNumber" , pageNumber);
					
			return "redirect:/loginForm.mb";	
			
		}else {
			
			ProductBean pb = productDao.detailViewByNum(num);
//			mav.addObject("product", pb);
//			mav.addObject("pageNumber", pageNumber);
//			mav.addObject("whatColumn", whatColumn);
//			mav.addObject("keyword", keyword);
//			mav.setViewName(getPage); // productUpdateForm
//
//			return mav;
			model.addAttribute("product" , pb);
			model.addAttribute("pageNumber" , pageNumber);
			return getPage;
		}
	}
	
	@RequestMapping(value=command , method=RequestMethod.POST)
	public ModelAndView update(
			@ModelAttribute("product") @Valid ProductBean product, BindingResult result,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("whatColumn") String whatColumn,
			@RequestParam("keyword") String keyword
			) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			if(product.getImage().equals("")) {
				product.setImage(product.getUpload2());
			}
			return mav;
		}
		int cnt = -1;
		
		cnt = productDao.updateProduct(product);
		
		
		
		MultipartFile multi = product.getUpload();
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		
		if(cnt != -1) {
			
			String deletePath = servletContext.getRealPath("/resources/uploadImage/");
			File file = new File(deletePath+File.separator+product.getUpload2());
			
			System.out.println("File : " + file);
			
			if(file.exists()) {
				file.delete();
			}
			
			mav.setViewName(gotoPage);
			File destination = new File(uploadPath + File.separator + multi.getOriginalFilename());
			try {
				//올리는 작업
				multi.transferTo(destination);
			}catch (IllegalStateException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		else {
			mav.setViewName(getPage);
		}
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		
		return mav;
	}
}
