package org.semmellitis.chesar.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Csa implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -5099762721768766803L;

  @Id
  @SequenceGenerator(name = "csa-sec", sequenceName = "CSA_SEC", initialValue = 1,
      allocationSize = 12)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csa-sec")
  private Long id;

  private String name;

  @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
  private Substance substance;

  private Csa() {}

  public Csa(Substance substance, String name) {
    this.name = name;
    this.substance = substance;
  }

  public Long getId() {
    return id;
  }

  public Substance getSubstance() {
    return substance;
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
