package org.semmellitis.chesar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.semmellitis.chesar.annotation.BusinessKey;

@Entity
public class Substance {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "substance-sec")
  @SequenceGenerator(name = "substance-sec", sequenceName = "SUBSTANCE_SEC", initialValue = 1,
      allocationSize = 12)
  private long id;

  @BusinessKey
  @Column(unique = true)
  private String uuid;

  @SuppressWarnings("unused")
  private Substance() {};

  public Substance(String uuid) {
    this.uuid = uuid;
  }

  public String getUuid() {
    return uuid;
  }
}
