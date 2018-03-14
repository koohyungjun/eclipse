package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindService {
	@Autowired
	SqlSessionTemplate template;
	
	public Map findByIdMailAndPass(Map data) {
		Map map = template.selectOne("member.getByIdmailAndPassword", data);
		return map;
	}
	
	
}
