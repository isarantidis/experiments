package org.semmellitis.chesar.service;

import javax.transaction.Transactional;

import org.semmellitis.chesar.command.GreetCommand;

import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

@Consumer
public class ChemicalEntityCommandHandler implements CommandHandler {

  protected ChemicalEntityCommandHandler() {}

  @Selector(value = "greet", reactor = "@rootReactor")
  @Transactional(value = Transactional.TxType.MANDATORY)
  public void handleCommand(Event<GreetCommand> command) {
    // Command should be validated here or controller layer?
    // Load the domain entity identified by the command. Only one entity should be retrieved.
    // Perform action against the entity. The command arguments will be validated by the entity
    // Process events
    System.out.println(command);
  }
}
