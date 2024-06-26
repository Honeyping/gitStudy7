package member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import member.model.MemberDao;

@Controller
public class MemberDeleteController {
	final String command = "/delete.mb";
	final String gotoPage = "redirect:/memberList.mb";

	@Autowired
	MemberDao memberDao;
	
	@RequestMapping(value=command)
	public String delete(@RequestParam(value="id",required=true)String id,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("whatColumn") String whatColumn,
			@RequestParam("keyword") String keyword,
			RedirectAttributes redirectAttributes) {
		Integer cnt = -1;
		cnt = memberDao.deleteMember(id);
		try {
			String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
			String encodedWhatColumn = URLEncoder.encode(whatColumn, "UTF-8");

			String redirectUrl = "redirect:/list.mb?pageNumber=" + pageNumber + "&whatColumn=" + encodedWhatColumn + "&keyword=" + encodedKeyword;
			return redirectUrl;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("url인코딩 오류");
		return gotoPage;
		}
	}
}
