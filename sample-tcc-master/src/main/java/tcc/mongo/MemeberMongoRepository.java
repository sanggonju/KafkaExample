package tcc.mongo;

import java.util.List;

import org.springframework.stereotype.Repository;

import tcc.vo.MemberMongo;

@Repository
//public interface MemeberMongoRepository extends MongoRepository<MemberMongo, String>{
public interface MemeberMongoRepository {
	public List<MemberMongo> findAll();
	public void insertData(MemberMongo insertData);
}
