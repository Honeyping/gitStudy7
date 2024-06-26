package member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberUpdateController {
	final String command = "/update.mb";
	final String getPage = "memberUpdateForm";
	final String gotoPage = "redirect:/memberList.mb";
	
	@Autowired
	MemberDao memberDao;
	
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String update(@RequestParam("id") String id, 
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("whatColumn") String whatColumn,
			@RequestParam("keyword") String keyword,
			Model model) {
		
		MemberBean member = memberDao.getMember(id);
		model.addAttribute("member",member);
		
		return getPage;
	}
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("member") @Valid MemberBean member, 
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("whatColumn") String whatColumn,
			@RequestParam("keyword") String keyword,
			BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		memberDao.updateMember(member);
		
		String redirectUrl = "redirect:/memberList.mb?pageNumber=" + pageNumber + "&whatColumn=" + whatColumn + "&keyword=" + keyword;
		mav.setViewName(redirectUrl);
		return mav;
	
	}
}
