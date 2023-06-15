package test.com.mini.board;

import java.util.List;

public interface BoardDAO {


	public List<BoardVO> findAll();
	public BoardVO findOne(BoardVO vo);
	public int insert(BoardVO vo);
	public int update(BoardVO vo);
	public int delete(BoardVO vo);
	public List<BoardVO> findAll2(int page,int limit);
	public List<BoardVO> searchList(String searchKey, String searchWord);
	public List<BoardVO> findAllSearch(int page, int limit, String searchKey, String searchWord);
}
