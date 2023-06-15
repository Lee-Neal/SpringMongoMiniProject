package test.com.mini.member;

import java.util.List;

public interface MemberDAO {

	public MemberVO findOne(MemberVO vo);

	public int insert(MemberVO vo);
	
	public List<MemberVO> searchList(String searchKey, String searchWord);
	
	public List<MemberVO> findAll2(int page, int limit);

	public int updateOne(MemberVO vo);

	public int deleteOne(MemberVO vo);

	public List<MemberVO> findAllSearch(int page, int limit, String searchKey, String searchWord);

}
