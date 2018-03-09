package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.GreetService;
import total.service.JoinService;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	GreetService greetService;
	@Autowired
	JoinService joinService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String joinGetHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "join";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map<String, String> param, 
				Model model, HttpSession session) {
		try {
			boolean rst= joinService.addNewOne(param);
			if(rst) {
				session.setAttribute("logon", param.get("id"));
				return "redirect:/";
			}
			throw new Exception();
		}catch(Exception e) {
			model.addAttribute("err", "계정생성에서 문제가 있었습니다.");
			return "join";
		}
	}
}












