package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardDao;

@Controller
public class BoardDeleteController {
	
	@Autowired
	BoardDao boardDao;
	
	private final String command = "/delete.brd";
	private final String getPage = "deleteForm";
	private final String gotoPage = "redirect:/list.brd";
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String deleteForm(@RequestParam(value="num") int num,
							@RequestParam("pageNumber") int pageNumber,
							@RequestParam("whatColumn") String whatColumn,
							@RequestParam("keyword") String keyword) {
		System.out.println("GET");
		System.out.println("num: " + num + ", pageNumber: " + pageNumber + 
                ", whatColumn: " + whatColumn + ", keyword: " + keyword);
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="num") int num,
						@RequestParam("pageNumber") int pageNumber,
						@RequestParam("whatColumn") String whatColumn,
						@RequestParam("keyword") String keyword,
						HttpServletRequest request,
						HttpServletResponse response) {
		System.out.println("POST");
		System.out.println("num: " + num + ", pageNumber: " + pageNumber + 
                ", whatColumn: " + whatColumn + ", keyword: " + keyword);
		
		String passwd = request.getParameter("passwd");
		int cnt = boardDao.deleterArticle(num, passwd);
		ModelAndView mav = new ModelAndView();
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
