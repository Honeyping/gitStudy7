package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardBean;
import board.model.BoardDao;
import product.model.ProductBean;

@Controller
public class BoardUpdateController {
	
	@Autowired
	BoardDao boardDao;
	
	private final String command = "/update.brd";
	private final String getPage = "updateForm";
	private final String gotoPage = "redirect:/list.brd";
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String updateForm(@RequestParam(value="num", required = true) int num,
							@RequestParam(value="pageNumber", required = true) int pageNumber,
							@RequestParam(value="whatColumn", required = false) String whatColumn,
							@RequestParam(value="keyword", required = false) String keyword,
							HttpSession session,Model model) {
		
		ModelAndView mav = new ModelAndView();
		BoardBean bb = boardDao.viewContent(num);
		model.addAttribute("board" , bb);
		model.addAttribute("pageNumber" , pageNumber);
		
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("board") @Valid BoardBean board,
						BindingResult result,
						@RequestParam("pageNumber") int pageNumber,
						@RequestParam("whatColumn") String whatColumn,
						@RequestParam("keyword") String keyword,
						HttpServletResponse response) {
		System.out.println("updatePost");
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav; 
		}
		
		int cnt = -1;
		cnt = boardDao.updateArticle(board);
		
		if(cnt == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('비밀번호가 일치하지 않습니다.'); history.go(-1);</script>");
				out.flush();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		mav.setViewName(gotoPage);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		
		return mav;
	}

}
