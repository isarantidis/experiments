package org.semmellitis.chesar.domain;

public class SubstanceCreatedEvent {
  private long substanceId;

  public SubstanceCreatedEvent(Substance substance) {
    this.substanceId = substance.getId();
  }

  public long getSubstanceId() {
    return substanceId;
  }

}
