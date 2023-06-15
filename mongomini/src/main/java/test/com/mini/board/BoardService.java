package test.com.mini.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	BoardDAO dao;
	
	public int insert(BoardVO vo) {		
		return dao.insert(vo);
	}
	public BoardVO findOne(BoardVO vo) {		
		return dao.findOne(vo);
	}
	public List<BoardVO> searchList(String searchKey, String searchWord) {

		return dao.searchList(searchKey,searchWord);
	}
	public int update(BoardVO vo) {

		return dao.update(vo);
	}
	public int delete(BoardVO vo) {

		return dao.delete(vo);
	}
	public List<BoardVO> findAll2(int page, int limit) {

		return dao.findAll2(page,limit);
	}
	public List<BoardVO> findAllSearch(int page, int limit, String searchKey, String searchWord) {
		
		return dao.findAllSearch(page,limit,searchKey,searchWord);
	}
}
