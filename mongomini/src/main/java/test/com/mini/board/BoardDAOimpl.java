package test.com.mini.board;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOimpl implements BoardDAO {
	@Autowired
	MongoCollection<Document> board;

	@Override
	public List<BoardVO> findAll() {
		log.info("findAll()...");

		List<BoardVO> list = new ArrayList<BoardVO>();
		FindIterable<Document> docs = board.find();
		log.info("dics...{}", docs);

		for (Document doc : docs) {
			log.info("{}", doc);

			BoardVO vo = new BoardVO();
			vo.setBid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num"));
			vo.setTitle(doc.getString("title"));
			vo.setContent(doc.getString("content"));
			vo.setWriter(doc.getString("writer"));
			vo.setWdate(doc.getDate("wdate"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public BoardVO findOne(BoardVO vo) {
	    log.info("findOne()...{}", vo);

	    BoardVO vo2 = new BoardVO();

	    Bson filter = new Document("_id", new ObjectId(vo.getBid()));
	    FindIterable<Document> docs = board.find(filter);
	    log.info("docs...{}", docs);

	    for (Document doc : docs) {
	        log.info("doc...{}", doc);
	        vo2.setBid(doc.getObjectId("_id").toString());
	        vo2.setNum(doc.getInteger("num"));
	        vo2.setTitle(doc.getString("title"));
	        vo2.setContent(doc.getString("content"));
	        vo2.setWriter(doc.getString("writer"));
	        vo2.setWdate(doc.getDate("wdate"));
	    	}
	    log.info("vo2...{}", vo2);
	    return vo2;
	}

	@Override
	public int insert(BoardVO vo) {
		log.info("insert()...{}", vo);
		int flag = 0;

		try {
			// 가장 큰 num 값을 조회
		    Document maxNumDoc = board.find().sort(Sorts.descending("num")).first();
		    int maxNum = maxNumDoc != null ? maxNumDoc.getInteger("num") : 0;

		    // 다음 번호 생성
		    int nextNum = maxNum + 1;
			
			Document doc = new Document();
		    doc.put("num", nextNum);
		    doc.put("title", vo.getTitle());
		    doc.put("content", vo.getContent());
		    doc.put("writer", vo.getWriter());
		    doc.put("wdate", vo.getWdate());
			board.insertOne(doc);
			flag = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public List<BoardVO> findAllSearch(int page, int limit, String searchKey, String searchWord) {
		log.info("findAllSearch...page:{},limit:{}", page,limit);
		log.info("findAllSearch...searchKey:{},searchWord:{}", searchKey, searchWord);

		List<BoardVO> list = new ArrayList<BoardVO>();

	    Bson sort = new Document("num", -1); // 순정렬 : 1, 역정렬 : -1
	    Bson filter = Filters.regex(searchKey, searchWord);
	    int skip = (page - 1) * limit;

	    FindIterable<Document> docs = board.find(filter).sort(sort).skip(skip).limit(limit);
	    log.info("docs...{}", docs);
	    
	    for (Document doc : docs) {
			log.info("{}", doc);

			BoardVO vo = new BoardVO();
			vo.setBid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num"));
			vo.setTitle(doc.getString("title"));
			vo.setContent(doc.getString("content"));
			vo.setWriter(doc.getString("writer"));
			vo.setWdate(doc.getDate("wdate"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public List<BoardVO> findAll2(int page,int limit) {
		log.info("findAll2...page:{},limit:{}", page,limit);

		List<BoardVO> list = new ArrayList<BoardVO>();

		Bson sort = new Document("num", -1); // 역정렬 호출
		int skip = (page - 1) * limit;

		FindIterable<Document> docs = board.find().sort(sort).skip(skip).limit(limit);
		log.info("dics...{}", docs);

		for (Document doc : docs) {
			log.info("{}", doc);

			BoardVO vo = new BoardVO();
			vo.setBid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num"));
			vo.setTitle(doc.getString("title"));
			vo.setContent(doc.getString("content"));
			vo.setWriter(doc.getString("writer"));
			vo.setWdate(doc.getDate("wdate"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public List<BoardVO> searchList(String searchKey, String searchWord) {
		log.info("searchList...searchKey:{},searchWord:{}", searchKey, searchWord);
		List<BoardVO> list = new ArrayList<BoardVO>();

		Bson sort = new Document("num", -1); // 순정렬 : 1, 역정렬 : -1
		Bson filter = Filters.regex(searchKey, searchWord);

		FindIterable<Document> docs = board.find(filter).sort(sort);

		for (Document doc : docs) {
			log.info("{}", doc);

			BoardVO vo = new BoardVO();
			vo.setBid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num"));
			vo.setTitle(doc.getString("title"));
			vo.setContent(doc.getString("content"));
			vo.setWriter(doc.getString("writer"));
			vo.setWdate(doc.getDate("wdate"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public int update(BoardVO vo) {
		log.info("update...{}", vo);
		int flag = 0;

		try {
			Bson filter = new Document("_id", new ObjectId(vo.getBid()));
			Bson update = Updates.combine(Updates.set("title", vo.getTitle()),
					Updates.set("content", vo.getContent()),
					Updates.set("wdate", vo.getWdate())
					);

			UpdateResult result = board.updateOne(filter, update);
			log.info("update result..{}", result);
			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public int delete(BoardVO vo) {
		log.info("delete...{}", vo);
		int flag = 0;

		try {
			Bson filter = new Document("_id", new ObjectId(vo.getBid()));
			DeleteResult result = board.deleteOne(filter);
			log.info("getDeletedCount...{}", result.getDeletedCount());
			flag = (int) result.getDeletedCount();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
