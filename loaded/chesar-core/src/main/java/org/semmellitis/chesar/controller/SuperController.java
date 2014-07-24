package org.semmellitis.chesar.controller;

import java.util.List;
import java.util.logging.Logger;

import org.semmellitis.chesar.domain.Substance;
import org.semmellitis.chesar.persistence.SubstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.Reactor;
import reactor.event.Event;

@RestController
public class SuperController {
  private static final Logger LOG = Logger.getLogger(SuperController.class.getName());

  @Autowired
  private Reactor rootReactor;

  @Autowired
  private SubstanceRepository substanceRepository;

  @RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
  public String home() {
    return "";
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

  @RequestMapping(value = "/hotdeploys", method = RequestMethod.GET,
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public HotDeploy hotDeployable(@RequestParam(required = true) boolean isHotDeployable) {

    HotDeploy hotDeploy = new HotDeploy();

    hotDeploy.setHotDeployable(isHotDeployable);
    rootReactor.notify("t.t", Event.<String>wrap("haha"));

    return hotDeploy;
  }

  @RequestMapping(value = "/substance", method = RequestMethod.PUT)
  public ResponseEntity<String> createSubstance(@RequestBody String uuid) {
    Substance d = new Substance(uuid);
    substanceRepository.save(d);
    return new ResponseEntity<String>(uuid, HttpStatus.OK);
  }

  @RequestMapping(value = "/substance/{uuid}", method = RequestMethod.GET)
  public Substance getSubstance(@PathVariable String uuid) {
    return substanceRepository.findByUuid(uuid);
  }

  @RequestMapping(value = "/substance", method = RequestMethod.GET)
  public List<Substance> getSubstances() {
    return substanceRepository.findAll();
  }
}
