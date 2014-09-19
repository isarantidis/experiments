package org.semmellitis.chesar.ui.server;

import org.semmellitis.chesar.ui.shared.ChesarResponse;
import org.semmellitis.chesar.ui.shared.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.Reactor;
import reactor.event.Event;

/**
 * The server side implementation of the RPC service.
 */
@RestController
@RequestMapping("/my/api/command")
public class CommandController {

  @Autowired
  private Reactor rootReactor;

  @RequestMapping(value = "/{commandId}", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ChesarResponse> processCommand(@RequestBody Command command,
      @PathVariable String commandId) {
    ChesarResponse chResponse = new ChesarResponse();
    ResponseEntity<ChesarResponse> response = null;
    try {
      rootReactor.notify(commandId, Event.<Command>wrap(command));
      response = new ResponseEntity<ChesarResponse>(chResponse, HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<ChesarResponse>(chResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }


}
