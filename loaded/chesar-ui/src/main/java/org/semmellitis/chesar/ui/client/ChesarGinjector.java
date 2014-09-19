package org.semmellitis.chesar.ui.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(value = {ChesarGinModule.class})
public interface ChesarGinjector extends Ginjector {
  Messages getMessages();

  CommandService getCommandService();
}
