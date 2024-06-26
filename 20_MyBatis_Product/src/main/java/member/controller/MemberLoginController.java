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
	//��ǰ�߰��ϱ� ��ư�� �����µ� �α��� ������ ���� �� 
	// productList.jsp=>�߰��ϱ�=>ProductInsertController=>redirect:/loginForm.mb
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String loginForm() {
		return getPage;
	}
	
	// member\memberLoginForm.jsp���� �α��� Ŭ��(id,password)
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView loginForm(MemberBean member,
							HttpSession session,
							HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		/*
		 * if(result.hasErrors()) { mav.setViewName(getPage); return mav; }
		 */
		// id:kim, password:1234
		// alert(�ش���̵� �������� �ʽ��ϴ�.)
		// alert(��й�ȣ�� �߸��ƽ��ϴ�.)
		MemberBean mb = memberDao.getMember(member.getId());
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(mb == null){ // �ش� ���̵� �������� �ʴ´�.
				out.println("<script>");
				out.println("alert('�ش� ���̵�� �������� �ʽ��ϴ�.');");
				out.println("</script>");
				out.flush();
				//mav.setViewName(getPage);
				return new ModelAndView( getPage ) ;
				
			}else{ // �ش� ���̵� �����Ѵ�.
				if(mb.getPassword().equals(member.getPassword())) { // ��� ��ġ
					
					session.setAttribute("loginInfo", mb); // loginInfo:�α����� ����� ����
					System.out.println("��� ��ġ");
					System.out.println("destination:"+(String)session.getAttribute("destination"));
				
					return new ModelAndView( (String)session.getAttribute("destination") ) ;
					
				}else { // ��� ����ġ
					System.out.println("��� ����ġ");
					out.println("<script>");
					out.println("alert('��� ����ġ�Դϴ�.');");
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
