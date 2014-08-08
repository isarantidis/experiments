package org.semmellitis.chesar.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Csa implements Serializable {


  @Id
  @SequenceGenerator(name = "csa-sec", sequenceName = "CSA_SEC", initialValue = 1,
      allocationSize = 12)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csa-sec")
  private Long id;

  private String name;

  @ManyToOne(targetEntity = Substance.class)
  private long substanceId;

  private Csa() {}

  public Csa(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public long getSubstanceId() {
    return substanceId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    Csa rhs = (Csa) obj;
    return id == rhs.id;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + id.hashCode();
  }
}
