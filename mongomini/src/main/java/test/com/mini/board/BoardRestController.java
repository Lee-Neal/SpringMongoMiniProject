package test.com.mini.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardRestController {
	
	@Autowired
	BoardService service;
	
	@ResponseBody
	@RequestMapping(value = "/board_findAll2.do", method = RequestMethod.GET)
	public List<BoardVO> findAll2(int page) {
		log.info("findAll2.do...page:{}",page);
		int limit=10;
		
		List<BoardVO> list = service.findAll2(page,limit);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_findAllSearch.do", method = RequestMethod.GET)
	public List<BoardVO> board_findAllSearch(int page,String searchKey,String searchWord) {
		log.info("findAllSearch.do...page:{}",page);
		log.info("findAllSearch.do...serachKey{},searchWord:{}",searchKey,searchWord);
		int limit=10;
		
		List<BoardVO> list = service.findAllSearch(page,limit,searchKey,searchWord);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_searchList.do", method = RequestMethod.GET)
	public List<BoardVO> searchList(String searchKey,String searchWord) {
		log.info("searchList.do...serachKey{},searchWord:{}",searchKey,searchWord);
		
		List<BoardVO> list = service.searchList(searchKey,searchWord);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_insertOK.do", method = RequestMethod.GET)
	public Map<String, Integer> insertOK(BoardVO vo) {
		log.info("insertOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.insert(vo);
		
		map.put("result", result);
		log.info("insertOK.do..result{}",result);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_findOne.do", method = RequestMethod.GET)
	public BoardVO findOne(HttpServletRequest request) {
		log.info("findOne.do..");
		
		BoardVO vo = new BoardVO();
		vo.setBid(request.getParameter("bid"));
		log.info("findOne.do..{}",vo);
		BoardVO vo2 = service.findOne(vo);
		
		return vo2;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_updateOK.do", method = RequestMethod.GET)
	public Map<String, Integer> updateOK(BoardVO vo) {
		log.info("updateOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.update(vo);
		log.info("updateOK.do..result:{},Bid:{}",result,vo.getBid());

		map.put("result", result);

		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/board_deleteOK.do", method = RequestMethod.GET)
	public Map<String, Integer> deleteOK(BoardVO vo) {
		log.info("deleteOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.delete(vo);
		
		map.put("result", result);

		return map;
	}
	
}
