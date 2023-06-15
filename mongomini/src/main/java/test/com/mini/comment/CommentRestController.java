package test.com.mini.comment;

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
public class CommentRestController {
	
	@Autowired
	CommentService service;
	
	
	@ResponseBody
	@RequestMapping(value = "/comment_insertOK.do", method = RequestMethod.GET)
	public Map<String, Integer> insertOK(CommentVO vo) {
		log.info("insertOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.insert(vo);
		
		map.put("result", result);
		log.info("insertOK.do..result{}",result);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment_updateOK.do", method = RequestMethod.GET)
	public Map<String, Integer> updateOK(CommentVO vo) {
		log.info("updateOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.update(vo);
		log.info("updateOK.do..result:{},Bid:{}",result,vo.getBid());

		map.put("result", result);

		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment_deleteOK.do", method = RequestMethod.GET)
	public Map<String, Integer> deleteOK(CommentVO vo) {
		log.info("deleteOK.do..{}",vo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.delete(vo);
		
		map.put("result", result);

		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment_findAll.do", method = RequestMethod.GET)
	public List<CommentVO> findAll(HttpServletRequest request) {
		log.info("findAll.do..");
		
		CommentVO vo = new CommentVO();
		vo.setBid(request.getParameter("bid"));
		log.info("Comment_findAll.do..{}",vo);
		List<CommentVO> list = service.findAll(vo);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Comment_findOne.do", method = RequestMethod.GET)
	public CommentVO findOne (CommentVO vo) {
		log.info("findOne.do..");
		
		CommentVO vo2 = service.findOne(vo);
		
		return vo2;
	}
}
