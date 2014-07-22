package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.Reactor;

@RestController
public class ImportController {
	@Autowired
	private Reactor rootReactor;
	
	@RequestMapping(value = "/import")
    public String home () {
        return "";
    }
}
