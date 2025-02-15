package in.kapurit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

	
	@GetMapping("/hello")
    public String hello() {
        return "Unlogged: Open-source tool for Java devs to instantly mock DB/APIs, auto-generate unit tests from API traffic, monitor method performance, and save/replay method inputs & outputs. Integrates with CI for code coverage. Install to boost your Java DevOps! ";
    }
}
