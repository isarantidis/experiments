package org.semmellitis.chesar.service;

import javax.transaction.Transactional;

import org.semmellitis.chesar.domain.ChesarStructureEntry;
import org.semmellitis.chesar.domain.CsaAddedEvent;
import org.semmellitis.chesar.domain.SubstanceCreatedEvent;
import org.semmellitis.chesar.persistence.ChesarStructureEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

@Service
@Consumer
public class ChesarStructureService {
  @Autowired
  private ChesarStructureEntryRepository repository;

  @Selector(value = "CREATE_SUBSTANCE_COMMAND", reactor = "@rootReactor")
  @Transactional(value = Transactional.TxType.MANDATORY)
  public void handleSubstanceCreated(Event<SubstanceCreatedEvent> event) {
    SubstanceCreatedEvent eve = event.getData();
    ChesarStructureEntry entry =
        new ChesarStructureEntry(eve.getSubstanceId(), "Substance", eve.getSubstanceId(),
            "Substance");
    repository.save(entry);
  }

  @Selector(value = "ADD_CSA_COMMAND", reactor = "@rootReactor")
  @Transactional(value = Transactional.TxType.MANDATORY)
  public void handleCsaAdded(Event<CsaAddedEvent> event) {
    CsaAddedEvent eve = event.getData();
    ChesarStructureEntry entry =
        new ChesarStructureEntry(eve.getSubstanceId(), "Substance", eve.getCsaId(), "Csa");
    repository.save(entry);
  }
}
