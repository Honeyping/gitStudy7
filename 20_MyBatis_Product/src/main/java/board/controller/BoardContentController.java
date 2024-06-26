package board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardContentController {
	
	@Autowired
	BoardDao boardDao;
	
	private final String command = "/content.brd";
	private final String getPage = "content";
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String content(@RequestParam(value =  "num", required = true) int num,
							@RequestParam("pageNumber") int pageNumber,
							@RequestParam(value="whatColumn",required=false) String whatColumn,
							@RequestParam(value="keyword",required=false) String keyword,
							HttpSession session,
							Model model) {
		
		if(session.getAttribute("loginInfo") == null) {
			session.setAttribute("destination", "redirect:/content.brd?num=" + num + "&pageNumber=" + pageNumber + "&whatColumn=" + whatColumn + "&keyword=" + keyword);
			return "redirect:/loginForm.mb";
		}else {
			
		int readcount = boardDao.getReadCount(num);
		boardDao.updateReadCount(num);
		BoardBean bb = boardDao.viewContent(num);
		model.addAttribute("board", bb);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("whatColumn", whatColumn);
		model.addAttribute("keyword", keyword);
		
		return getPage;
		}
	}
	
}
