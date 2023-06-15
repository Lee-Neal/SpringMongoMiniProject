package test.com.mini.comment;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CommentDAOimpl implements CommentDAO {

	@Autowired
	MongoCollection<Document> comment;

	@Override
	public List<CommentVO> findAll(CommentVO vo) {
		log.info("findAll()...");

		List<CommentVO> list = new ArrayList<CommentVO>();

		Bson sort = new Document("cnum", 1);
		Bson filter = new Document("bid", vo.getBid());
		log.info("filter...{}", filter.toString());

		FindIterable<Document> docs = comment.find(filter).sort(sort);
		log.info("docs...{}", docs.toString());

		for (Document doc : docs) {
			log.info("doc: {}", doc.toJson());
			CommentVO vo2 = new CommentVO();
			vo2.setBid(doc.getObjectId("_id").toString());
			vo2.setCnum(doc.getInteger("num"));
			vo2.setContent(doc.getString("content"));
			vo2.setWriter(doc.getString("writer"));
			vo2.setWdate(doc.getDate("wdate"));
			log.info("comment vo2:{}", vo2);
			list.add(vo2);
		}

		return list;
	}

	public CommentVO findOne(CommentVO vo) {
		log.info("findOne()...");

		Bson filter = new Document("_id", new ObjectId(vo.getBid()));
		log.info("filter...{}", filter.toString());

		FindIterable<Document> docs = comment.find(filter);
		log.info("docs...{}", docs.toString());
		CommentVO vo2 = new CommentVO();

		for (Document doc : docs) {
			log.info("{}", doc);
			vo2.setContent(doc.getString("content"));
			vo2.setWriter(doc.getString("writer"));
			vo2.setWdate(doc.getDate("wdate"));
		}

		return vo2;
	}

	@Override
	public int insert(CommentVO vo) {
		log.info("insert()...{}", vo);
		int flag = 0;

		try {
			// 가장 큰 num 값을 조회
			Document maxNumDoc = comment.find().sort(Sorts.descending("num")).first();
			int maxNum = maxNumDoc != null ? maxNumDoc.getInteger("num") : 0;

			// 다음 번호 생성
			int nextNum = maxNum + 1;

			Document doc = new Document();
			doc.put("num", nextNum);
			doc.put("bid", vo.getBid());
			doc.put("content", vo.getContent());
			doc.put("writer", vo.getWriter());
			doc.put("wdate", vo.getWdate());
			comment.insertOne(doc);
			flag = 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public int update(CommentVO vo) {
		log.info("comment_update....{}", vo);
		int flag = 0;

		try {
			Bson filter = new Document("num", (vo.getCnum()));
			Bson update = Updates.combine(Updates.set("content", vo.getContent()),
					Updates.set("wdate", vo.getWdate()));

			UpdateResult result = comment.updateOne(filter, update);
			log.info("update result..{}", result);
			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public int delete(CommentVO vo) {
		log.info("comment_delete...",vo);
		int flag = 0;

		try {
			Bson filter = new Document("num", vo.getCnum());

			DeleteResult result = comment.deleteOne(filter);

			log.info("getDeletedCount...{}", result.getDeletedCount());

			flag = (int) result.getDeletedCount();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

}
