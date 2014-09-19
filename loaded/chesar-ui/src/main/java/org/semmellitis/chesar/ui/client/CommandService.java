package org.semmellitis.chesar.ui.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.semmellitis.chesar.ui.shared.ChesarResponse;
import org.semmellitis.chesar.ui.shared.Command;

@Path("api/command")
public interface CommandService extends RestService {

  @POST
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  void sendCommand(@PathParam("id") String id, Command command,
      MethodCallback<ChesarResponse> callback);

}
