package board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

@Controller
public class BoardReplyController {
	
	@Autowired
	BoardDao boardDao;
	
	private final String command = "/reply.brd";
	private final String getPage = "replyForm";
	private final String gotoPage = "redirect:/list.brd";
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String replyForm(@RequestParam("ref") int ref,
							@RequestParam("re_step") int re_step,
							@RequestParam("re_level") int re_level,
							@RequestParam("pageNumber") int pageNumber,
							@RequestParam(value="whatColumn",required=false) String whatColumn,
							@RequestParam(value="keyword",required=false) String keyword,
							Model model) {
		
		System.out.println("reply GET");
		model.addAttribute("ref", ref);
        model.addAttribute("re_step", re_step);
        model.addAttribute("re_level", re_level);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("whatColumn", whatColumn);
        model.addAttribute("keyword", keyword);
        
		return getPage;
	}
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView reply(@ModelAttribute("board") @Valid BoardBean board,
								BindingResult result,
								@RequestParam("ref") int ref,
								@RequestParam("re_step") int re_step,
								@RequestParam("re_level") int re_level,
								@RequestParam("pageNumber") int pageNumber,
								@RequestParam(value="whatColumn",required=false) String whatColumn,
								@RequestParam(value="keyword",required=false) String keyword,
								HttpServletRequest request) {
		
		System.out.println("replyPOST");
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav; 
		}
		
		board.setRef(ref);
        board.setRe_step(re_step+1);
        board.setRe_level(re_level + 1);
        board.setIp(request.getRemoteAddr());
		//boardDao.updateReplyStep(ref, re_step);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put("ref", ref);
	    map.put("re_step", re_step);
	    boardDao.updateReplyStep(map);
	    int cnt = boardDao.replyArticle(board);
	    
		mav.setViewName(gotoPage);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.addObject("ref",ref);
		mav.addObject("re_level",re_level);
		mav.addObject("re_step",re_step);
		mav.addObject("pageNumber",pageNumber);
		return mav;
	}
}
