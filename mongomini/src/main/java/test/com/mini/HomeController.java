package test.com.mini;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import test.com.mini.board.BoardService;
import test.com.mini.board.BoardVO;
import test.com.mini.member.MemberService;
import test.com.mini.member.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	MemberService mservice;
	
	@Autowired
	BoardService bservice;
	
	@RequestMapping(value ={"/","/home.do"}, method = RequestMethod.GET)
	public String home() {
		log.info("Welcome home!");
		
		return "home";
	}
	
	@RequestMapping(value = "/member_insert.do", method = RequestMethod.GET)
	public String member_insert() {
		log.info("member_insert.do");
		
		return "member/insert";
	}
	
	@RequestMapping(value = "/m_findAll.do", method = RequestMethod.GET)
	public String member_findAll() {
		log.info("member_findAll.do");
		
		return "member/findall";
	}
	
	@RequestMapping(value = "/b_findAll.do", method = RequestMethod.GET)
	public String board_findAll() {
		log.info("board_findAll.do");
		
		return "board/findall";
	}
	
	@RequestMapping(value = "/m_findOne.do", method = RequestMethod.GET)
	public String member_findOne() {
		log.info("m_findOne.do");
		
		return "member/findOne";
	}
	
	@RequestMapping(value = "/b_findOne.do", method = RequestMethod.GET)
	public String board_findOne() {
		log.info("b_findOne.do");
		
		return "board/findOne";
	}
	
	@RequestMapping(value = "/board_insert.do", method = RequestMethod.GET)
	public String board_insert() {
		log.info("board_insert.do");
		
		return "board/insert";
	}
	
}
