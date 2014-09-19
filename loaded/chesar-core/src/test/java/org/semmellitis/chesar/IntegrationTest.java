package org.semmellitis.chesar;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.semmellitis.chesar.command.GreetCommand;
import org.semmellitis.chesar.domain.Substance;
import org.semmellitis.chesar.domain.SubstanceCreatedEvent;
import org.semmellitis.chesar.persistence.CsaRepository;
import org.semmellitis.chesar.persistence.SubstanceRepository;
import org.semmellitis.chesar.service.ChemicalEntityCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reactor.core.Reactor;
import reactor.event.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class IntegrationTest {

  @Autowired
  private SubstanceRepository substanceRepository;

  @Autowired
  private CsaRepository csaRepository;

  @Autowired
  private Reactor rootReactor;

  @Autowired
  private ChemicalEntityCommandHandler cec;

  @Test
  @Transactional
  public void substanceSave() {
    Substance sub = new Substance("dd");
    substanceRepository.save(sub);
    rootReactor.notify("CREATE_SUBSTANCE_COMMAND",
        Event.<SubstanceCreatedEvent>wrap(new SubstanceCreatedEvent(sub)));
    Assert.assertNotNull(substanceRepository.findByUuid("dd"));
  }

  @Test
  @Transactional
  public void addCsa() {
    rootReactor.notify("greet", Event.<GreetCommand>wrap(new GreetCommand()));
  }
}
