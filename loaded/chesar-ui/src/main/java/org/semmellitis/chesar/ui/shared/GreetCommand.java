package org.semmellitis.chesar.ui.shared;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("greet")
public class GreetCommand extends Command {
  public static final String ID = "greet";
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
