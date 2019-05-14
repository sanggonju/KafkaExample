package tcc.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tcc.kafka.KafkaImpl;
import tcc.mongo.MemeberMongoRepository;
import tcc.service.DemoService;
import tcc.vo.Member;
import tcc.vo.MemberMongo;

@RestController
public class DemoController {

	@Autowired
	private DemoService service;
	
	@Autowired
	private KafkaImpl kafkaImpl;
	
	private static final String TOPIC = "save1";
	
	@Autowired
	private MemeberMongoRepository memeberMongoRepository;
	
	@RequestMapping(value="/getDual",  method=RequestMethod.GET)
	public Member getDual() throws Exception {
		return service.getDual();
	}

	@RequestMapping(value="/test",  method=RequestMethod.GET)
	public Member test() throws Exception {
		Member ge = new Member("", "");
		return ge;		
	}
	@RequestMapping(value="/MongoAll",  method=RequestMethod.GET)
	public MemberMongo testMongo() throws Exception {
		MemberMongo memberMongo = new MemberMongo();
		memberMongo.setList(memeberMongoRepository.findAll());
		return memberMongo;		
	}
	
	
	
	@RequestMapping(value="/MongoAll2",  method=RequestMethod.GET)
	public List<MemberMongo> testMongo2() throws Exception {
		return memeberMongoRepository.findAll();
	}
	@RequestMapping(value="/insert",  method=RequestMethod.POST)
	public void insert(@RequestBody Map<String,Object> data) throws Exception {
		MemberMongo mem = (MemberMongo) data; 
		memeberMongoRepository.insertData(mem);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/publish",  method=RequestMethod.POST)
	public String post(@RequestBody Map<String,Object> data) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", data.get("id"));
        jsonObject.put("name", data.get("name"));
        String JSON = jsonObject.toString();
//		String ss= "{\""+"id\":" + "\""+data.get("id")+"\", " + "\"name\":\""+data.get("name")+"\"}";
		kafkaImpl.send(TOPIC,JSON);
		return "Published successfully";
	}
}
