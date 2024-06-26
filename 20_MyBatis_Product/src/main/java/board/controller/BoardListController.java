package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;
import utility.Paging;

@Controller
public class BoardListController {
	
	private final String command = "/list.brd";
	private final String getPage = "list";
	
	@Autowired
	BoardDao boardDao;
	
	@RequestMapping(command)
	public String list(@RequestParam(value="whatColumn", required=false) String whatColumn,
						@RequestParam(value="keyword", required=false) String keyword,
						@RequestParam(value="pageNumber", required=false) String pageNumber,
						HttpServletRequest request,
						Model model) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%" + keyword + "%");

		int totalCount = boardDao.getArticleCount(map);
		System.out.println("totalCount"+totalCount);
		String url = request.getContextPath() + this.command;

		Paging pageInfo = new Paging(pageNumber, null, totalCount, url, whatColumn, keyword);

		List<BoardBean> boardLists = boardDao.getArticle(map, pageInfo);
		model.addAttribute("boardLists", boardLists);
		model.addAttribute("pageInfo", pageInfo);

		return getPage;
	}
}
