package test.com.mini.member;

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
public class MemberDAOimpl implements MemberDAO {

	@Autowired
	MongoCollection<Document> member;
	
	@Override
	public List<MemberVO> findAllSearch(int page, int limit, String searchKey, String searchWord) {
	    log.info("findAllSearch()....{},{}", searchKey, searchWord);
	    log.info("findAllSearch()....page:{},limit:{}", page, limit);

	    List<MemberVO> list = new ArrayList<MemberVO>();

	    Bson sort = new Document("num", -1); // 순정렬 : 1, 역정렬 : -1
	    Bson filter = Filters.regex(searchKey, searchWord);
	    int skip = (page - 1) * limit;

	    FindIterable<Document> docs = member.find(filter).sort(sort).skip(skip).limit(limit);
	    log.info("docs...{}", docs);

	    for (Document doc : docs) {
	        log.info("{}", doc);

	        MemberVO vo = new MemberVO();
	        vo.setMid(doc.getObjectId("_id").toString());
	        vo.setNum(doc.getInteger("num", 0));
	        vo.setId(doc.getString("id"));
	        vo.setPw(doc.getString("pw"));
	        vo.setName(doc.getString("name"));
	        vo.setTel(doc.getString("tel"));
	        list.add(vo);
	    }

	    return list;
	}
	
	@Override
	public List<MemberVO> searchList(String searchKey, String searchWord) {
		log.info("searchList()....{},{}", searchKey, searchWord);

		List<MemberVO> list = new ArrayList<MemberVO>();

		Bson sort = new Document("num", -1); // 순정렬 : 1, 역정렬 : -1
		Bson filter = Filters.regex(searchKey, searchWord);

		FindIterable<Document> docs = member.find(filter).sort(sort);

		for (Document doc : docs) {
			log.info("{}", doc);

			MemberVO vo = new MemberVO();
			vo.setMid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num", 0));
			vo.setId(doc.getString("id"));
			vo.setPw(doc.getString("pw"));
			vo.setName(doc.getString("name"));
			vo.setTel(doc.getString("tel"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public int insert(MemberVO vo) {
	  log.info("insert()....{}", vo);
	  int flag = 0;

	  try {
	    // 가장 큰 num 값을 조회
	    Document maxNumDoc = member.find().sort(Sorts.descending("num")).first();
	    int maxNum = maxNumDoc != null ? maxNumDoc.getInteger("num") : 0;

	    // 다음 번호 생성
	    int nextNum = maxNum + 1;

	    // 새로운 문서 생성
	    Document doc = new Document();
	    doc.put("num", nextNum);
	    doc.put("id", vo.getId());
	    doc.put("pw", vo.getPw());
	    doc.put("name", vo.getName());
	    doc.put("tel", vo.getTel());

	    // 문서 삽입
	    member.insertOne(doc);

	    flag = 1;
	  } catch (Exception e) {
	    e.printStackTrace();
	  }

	  return flag;
	}

	@Override
	public MemberVO findOne(MemberVO vo) {
		log.info("findOne...{}", vo);

		MemberVO vo2 = new MemberVO();

		FindIterable<Document> docs = member.find(Filters.eq("_id", new ObjectId(vo.getMid())));
		log.info("docs...{}", docs);

		for (Document doc : docs) {
			log.info("{}", doc);
			vo2.setMid(doc.getObjectId("_id").toString());
			vo2.setNum(doc.getInteger("num", 0));
			vo2.setId(doc.getString("id"));
			vo2.setPw(doc.getString("pw"));
			vo2.setName(doc.getString("name"));
			vo2.setTel(doc.getString("tel"));
		}

		return vo2;
	}


	@Override
	public List<MemberVO> findAll2(int page, int limit) {
		log.info("findAll2()....page:{},limit:{}", page, limit);

		List<MemberVO> list = new ArrayList<MemberVO>();

		Bson sort = new Document("num", 1); // 순정렬 : 1, 역정렬 : -1
		int skip = (page - 1) * limit;

		FindIterable<Document> docs = member.find().sort(sort).skip(skip).limit(limit);
		log.info("dics...{}", docs);

		for (Document doc : docs) {
			log.info("{}", doc);

			MemberVO vo = new MemberVO();
			vo.setMid(doc.getObjectId("_id").toString());
			vo.setNum(doc.getInteger("num", 0));
			vo.setId(doc.getString("id"));
			vo.setPw(doc.getString("pw"));
			vo.setName(doc.getString("name"));
			vo.setTel(doc.getString("tel"));
			list.add(vo);
		}

		return list;
	}

	@Override
	public int updateOne(MemberVO vo) {
		log.info("updateOne()....{}", vo);
		int flag = 0;

		try {
			Bson filter = new Document("_id", new ObjectId(vo.getMid()));
			Bson update = Updates.combine(Updates.set("pw", vo.getPw()),
					Updates.set("name", vo.getName()),
					Updates.set("tel", vo.getTel()));

			UpdateResult result = member.updateOne(filter, update);
			log.info("update result..{}", result);
			flag = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	@Override
	public int deleteOne(MemberVO vo) {
		log.info("deleteOne()....{}", vo);
		int flag = 0;

		try {
			Bson filter = new Document("_id", new ObjectId(vo.getMid()));

			DeleteResult result = member.deleteOne(filter);

			log.info("getDeletedCount...{}", result.getDeletedCount());

			flag = (int) result.getDeletedCount();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	

}
