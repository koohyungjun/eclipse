package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
	@Autowired
	SqlSessionTemplate template;
	
	public boolean addNewOne(Map map) {
		return template.insert("member.addNewOne", map)==1;
	}
	
	public boolean existIdCheck(String id) {
		return template.selectOne("member.getById", id)!=null;
	}
	
	public boolean existEmailCheck(String email) {
		return template.selectOne("member.getByEmail", email)!=null;
	}
	
	
}
