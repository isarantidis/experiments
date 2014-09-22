package org.semmellitis.chesar.domain;


public class CsaAddedEvent {
  private Long substanceId;

  private Long csaId;

  public CsaAddedEvent(Csa csa) {
    this.csaId = csa.getId();
    this.substanceId = csa.getSubstance().getId();
  }

  public long getSubstanceId() {
    return substanceId;
  }

  public Long getCsaId() {
    return csaId;
  }
}
