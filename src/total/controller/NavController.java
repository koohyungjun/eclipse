package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.GreetService;

@Controller
@RequestMapping
public class NavController {
	@Autowired
	GreetService greetService;
	
	@RequestMapping("/join")
	public String joinHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "join";
	}
	
	@RequestMapping("/logon")
	public String logonHandle(Model model) {
		model.addAttribute("ment", greetService.make());
		return "logon";
	}
}
