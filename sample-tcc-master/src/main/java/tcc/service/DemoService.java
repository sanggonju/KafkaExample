package tcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.dao.DemoDao;
import tcc.vo.Member;

@Service
public class DemoService {
	@Autowired
	private DemoDao dao;
	
	public Member getDual() throws Exception {
		return dao.selectTest();
	}
	
	public void insert(Member member) throws Exception {
		dao.insertTest(member);
	}

}
