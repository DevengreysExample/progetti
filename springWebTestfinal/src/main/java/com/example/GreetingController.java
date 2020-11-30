package com.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

	@Autowired
	UtenteRepository repo;

	@Autowired
	AccountRepository arepo;
	List<Abc> Abcs = new ArrayList<Abc>();

	
	//mapping pagina home
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	//foreach 
	@PostMapping("/home")
	public String test(@RequestParam(name = "abc") String input, Model model) {
		System.out.println(input);
		Abcs.add(new Abc(input));
		model.addAttribute("Abcs", Abcs);
		return "home";
	}
	
	//mapping pagina index
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	//foreach con collegamento tabella utente
	@PostMapping("/")
	public String alien(@RequestParam(name = "utente") String input, Model model) {
		if(input!="") {
			Utente sampleUtente = new Utente();
			sampleUtente.setUsername(input);
			System.out.println("Id: "+sampleUtente.getId()+", name: "+sampleUtente.getUsername());
			repo.save(sampleUtente);
		}
     	model.addAttribute("Utenti", repo.findAll());
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public void login_vr(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
		Account a = new Account();
		a.setUsername(username);
		String cryptPass = Integer.toString(password.hashCode());
		a.setPassword(cryptPass);
    	Account user = arepo.findByUsername(username);
    	if(user.getPassword().equals(cryptPass)) {
    		System.out.println("accesso riuscito!");
    	}else {
    		System.out.println("accesso negato!");
    		
    	}
		
	}
	
	@GetMapping("/registrazione")
	public String registrazione() {
		return "registrazione";
	}
	
	@PostMapping("/registrazione")
	public void registrazione_vr(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model) {
	Account a = new Account();
	a.setPassword(Integer.toString(password.hashCode()));
	a.setUsername(username);
	arepo.save(a);
	}
	
	
	
	
	

}
