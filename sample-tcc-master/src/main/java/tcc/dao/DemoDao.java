package tcc.dao;

import org.springframework.stereotype.Repository;

import tcc.vo.Member;

@Repository
public interface DemoDao {
	public Member selectTest() throws Exception;
	public void insertTest(Member member) throws Exception;
}
