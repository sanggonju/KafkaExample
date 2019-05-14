package tcc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import tcc.mongo.MemeberMongoRepository;
import tcc.vo.MemberMongo;

@Service
public class MemeberMongoRepositoryImpl implements MemeberMongoRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<MemberMongo> findAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(MemberMongo.class);
	}

	@Override
	public void insertData(MemberMongo insertData) {
		mongoTemplate.save(insertData);
	}
	
	



}
