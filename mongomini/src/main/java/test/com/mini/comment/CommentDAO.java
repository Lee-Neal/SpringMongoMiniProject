package test.com.mini.comment;

import java.util.List;

public interface CommentDAO {
	public CommentVO findOne(CommentVO vo);
	public List<CommentVO> findAll(CommentVO vo);
	public int insert(CommentVO vo);
	public int update(CommentVO vo);
	public int delete(CommentVO vo);

}
