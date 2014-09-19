package org.semmellitis.chesar.ui.client;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.semmellitis.chesar.ui.shared.ChesarResponse;
import org.semmellitis.chesar.ui.shared.GetLocalizedMessagesCommand;

import com.google.gwt.i18n.client.Dictionary;
import com.google.inject.Inject;


/**
 * Interface to represent the messages contained in resource bundle:
 * C:/JOB/Workspaces/Experimental/experiments
 * /loaded/chesar-ui/src/main/resources/org/chesar/ui/client/Messages.properties'.
 */
public class Messages {

  public static final String nameField = "nameField";

  public static final String sendButton = "sendButton";

  public Dictionary dict;

  @Inject
  private CommandService commandService;

  public String text(String key) {
    if (dict == null) {
      commandService.sendCommand(GetLocalizedMessagesCommand.ID, new GetLocalizedMessagesCommand(),
          new MethodCallback<ChesarResponse>() {

            @Override
            public void onSuccess(Method method, ChesarResponse response) {
              // TODO Auto-generated method stub

            }

            @Override
            public void onFailure(Method method, Throwable exception) {
              // TODO Auto-generated method stub

            }
          });
    }
    return "FAFA";
  }

}
