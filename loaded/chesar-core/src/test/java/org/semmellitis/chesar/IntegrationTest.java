package org.semmellitis.chesar;

import static org.hamcrest.Matchers.hasSize;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.semmellitis.chesar.domain.Csa;
import org.semmellitis.chesar.domain.CsaAddedEvent;
import org.semmellitis.chesar.domain.Substance;
import org.semmellitis.chesar.domain.SubstanceCreatedEvent;
import org.semmellitis.chesar.persistence.CsaRepository;
import org.semmellitis.chesar.persistence.SubstanceRepository;
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
  private Reactor asyncReactor;

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
    Substance sub = new Substance("dd");
    substanceRepository.save(sub);
    rootReactor.notify("CREATE_SUBSTANCE_COMMAND",
        Event.<SubstanceCreatedEvent>wrap(new SubstanceCreatedEvent(sub)));
    Substance sub2 = substanceRepository.findByUuid("dd");
    Csa csa = new Csa("fast");
    sub2.addCsa(csa);
    substanceRepository.save(sub2);
    csaRepository.save(csa);
    rootReactor.notify("ADD_CSA_COMMAND", Event.<CsaAddedEvent>wrap(new CsaAddedEvent(csa)));
    Assert.assertThat(csaRepository.findBySubstanceId(sub2.getId()), hasSize(1));
  }
}
