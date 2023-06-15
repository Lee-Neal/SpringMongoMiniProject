package test.com.mini.member;

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
public class MemberRestController {

	@Autowired
	MemberService service;

	@ResponseBody
	@RequestMapping(value = "/member_findAll2.do", method = RequestMethod.GET)
	public List<MemberVO> findAll2(int page) {
		log.info("findAll2.do...page:{},limit:{}", page);
		int limit = 10;

		List<MemberVO> list = service.findAll2(page, limit);

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/member_findAllSearch.do", method = RequestMethod.GET)
	public List<MemberVO> member_findAllSearch(int page, String searchKey, String searchWord) {
		log.info("member_findAllSearch.do...page:{}", page);
		log.info("member_findAllSearch.do..{},{}", searchKey, searchWord);
		int limit = 10;

		List<MemberVO> list = service.findAllSearch(page, limit, searchKey, searchWord);

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/member_searchList.do", method = RequestMethod.GET)
	public List<MemberVO> searchList(String searchKey, String searchWord) {
		log.info("searchList.do..{},{}", searchKey, searchWord);

		List<MemberVO> list = service.searchList(searchKey, searchWord);

		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/member_findOne.do", method = RequestMethod.GET)
	public MemberVO findOne(HttpServletRequest request) {

		MemberVO vo = new MemberVO();
		vo.setMid(request.getParameter("mid"));
		MemberVO vo2 = service.findOne(vo);

		return vo2;
	}

	@ResponseBody
	@RequestMapping(value = "/member_insertOK.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> member_insertOK(MemberVO vo) {
		log.info("member_insertOK.do..{}", vo);

		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.insert(vo);
		map.put("result", result);

		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/member_updateOK.do", method = RequestMethod.POST)
	public Map<String, Integer> member_updateOK(MemberVO vo) {
		log.info("member_updateOK.do..{}", vo);

		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.updateOne(vo);
		map.put("result", result);

		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/member_deleteOK.do", method = RequestMethod.POST)
	public Map<String, Integer> member_deleteOK(MemberVO vo) {
		log.info("member_deleteOK.do..{}", vo);

		Map<String, Integer> map = new HashMap<String, Integer>();
		int result = service.deleteOne(vo);
		map.put("result", result);

		return map;
	}

}
