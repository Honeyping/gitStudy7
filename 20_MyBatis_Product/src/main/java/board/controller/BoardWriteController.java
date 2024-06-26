package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardWriteController {
	
	@Autowired
	BoardDao boardDao;
	
	private final String command = "/write.brd";
	private final String getPage = "writeForm";
	private final String gotoPage = "redirect:/list.brd";
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String writeForm(HttpSession session) {
		if(session.getAttribute("loginInfo") == null) { // 
			session.setAttribute("destination", "redirect:/write.brd");
			return "redirect:/loginForm.mb";
		}else {
			return getPage;
		}
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView write(@ModelAttribute("board") @Valid BoardBean board,
							BindingResult result,
							HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav; 
		}
		
		board.setIp(request.getRemoteAddr());
		
		int cnt = -1;
		cnt = boardDao.insertArticle(board);
		mav.setViewName(gotoPage);
		
		return mav;
	}
}
