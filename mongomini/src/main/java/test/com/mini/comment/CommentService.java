package test.com.mini.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO dao;
	
	public int insert(CommentVO vo) {		
		return dao.insert(vo);
	}
	public int update(CommentVO vo) {

		return dao.update(vo);
	}
	public int delete(CommentVO vo) {

		return dao.delete(vo);
	}
	public List<CommentVO> findAll(CommentVO vo){

		return dao.findAll(vo);
	}
	public CommentVO findOne(CommentVO vo) {

		return dao.findOne(vo);
	}
}
