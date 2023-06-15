package test.com.mini.member;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO dao;
	
	public int insert(MemberVO vo) {		
		return dao.insert(vo);
	}
	public MemberVO findOne(MemberVO vo) {		
		return dao.findOne(vo);
	}
	public List<MemberVO> searchList(String searchKey, String searchWord) {
		return dao.searchList(searchKey,searchWord);
	}
	public List<MemberVO> findAll2(int page, int limit) {
		return dao.findAll2(page,limit);
	}
	public int updateOne(MemberVO vo) {
		return dao.updateOne(vo);
	}
	public int deleteOne(MemberVO vo) {
		
		return dao.deleteOne(vo);
	}
	public List<MemberVO> findAllSearch(int page, int limit, String searchKey, String searchWord) {
		
		return dao.findAllSearch(page,limit,searchKey,searchWord);
	}
}
