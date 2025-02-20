package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {
	
	private final String command = "/loginForm.mb";
	private final String getPage = "memberLoginForm";
	
	@Autowired
	MemberDao memberDao;
	//상품추가하기 버튼을 눌렀는데 로그인 정보가 없을 때 
	// productList.jsp=>추가하기=>ProductInsertController=>redirect:/loginForm.mb
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String loginForm() {
		return getPage;
	}
	
	// member\memberLoginForm.jsp에서 로그인 클릭(id,password)
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView loginForm(MemberBean member,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		/*
		 * if(result.hasErrors()) { mav.setViewName(getPage); return mav; }
		 */
		// id:kim, password:1234
		// alert(해당아이디가 존재하지 않습니다.)
		// alert(비밀번호가 잘못됐습니다.)
		MemberBean mb = memberDao.getMember(member.getId());
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(mb == null){ // 해당 아이디가 존재하지 않는다.
				out.println("<script>");
				out.println("alert('해당 아이디는 존재하지 않습니다.');");
				out.println("</script>");
				out.flush();
				//mav.setViewName(getPage);
				return new ModelAndView( getPage ) ;
				
			}else{ // 해당 아이디가 존재한다.
				if(mb.getPassword().equals(member.getPassword())) { // 비번 일치
					
					session.setAttribute("loginInfo", mb); // loginInfo:로그인한 사람의 정보
					System.out.println("비번 일치");
					System.out.println("destination:"+(String)session.getAttribute("destination"));
				
					return new ModelAndView( (String)session.getAttribute("destination") ) ;
					
				}else { // 비번 불일치
					System.out.println("비번 불일치");
					out.println("<script>");
					out.println("alert('비번 불일치입니다.');");
					out.println("</script>");
					out.flush();
					return new ModelAndView( getPage ) ;
				}
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
		return mav;	
	}
}
