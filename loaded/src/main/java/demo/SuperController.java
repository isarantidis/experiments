package demo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.Reactor;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.spring.context.annotation.Selector;

@RestController
public class SuperController {
	private static final Logger LOG = Logger.getLogger(SuperController.class.getName());
	@Autowired
	private Reactor rootReactor;
	
    @RequestMapping("/")
    public String home () {
        return "Makrakomara";
    }
    
    class HotDeploy {
        boolean isHotDeployable = false;

        public boolean isHotDeployable() {
            return isHotDeployable;
        }

        public void setHotDeployable(boolean isHotDeployable) {
            this.isHotDeployable = isHotDeployable;
        }
    }

    @RequestMapping(
            value = "/hotdeploys",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
            ) 
    public HotDeploy hotDeployable (@RequestParam(required=true) boolean isHotDeployable) {

        HotDeploy hotDeploy = new HotDeploy();
        
            hotDeploy.setHotDeployable(isHotDeployable);
            rootReactor.notify("t.t", Event.<String>wrap("haha"));
        
        return hotDeploy;
    }
    
}
