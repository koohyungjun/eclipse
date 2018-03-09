package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.GreetService;

@Controller
public class IndexController {
	@Autowired
	GreetService greetService;
	
	@RequestMapping({"/index","/"})
	public String indexHandle(Model model ) {
		model.addAttribute("ment", greetService.make());
		return "index";
	}
	
}
