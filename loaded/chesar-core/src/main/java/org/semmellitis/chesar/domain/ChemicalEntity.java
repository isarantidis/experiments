package org.semmellitis.chesar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.semmellitis.chesar.annotation.BusinessKey;

@Entity
public class ChemicalEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chem-ent-sec")
  @SequenceGenerator(name = "chem-ent-sec", sequenceName = "CHEMICAL_ENTITY_SEC", initialValue = 1,
      allocationSize = 12)
  private long id;

  @BusinessKey
  @Column(unique = true)
  private String uuid;

  @SuppressWarnings("unused")
  private ChemicalEntity() {};

  public ChemicalEntity(String uuid) {
    this.uuid = uuid;
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
    ChemicalEntity rhs = (ChemicalEntity) obj;
    return uuid.equals(rhs.uuid);
  }

  @Override
  public int hashCode() {
    return 31 * 1 + uuid.hashCode();
  }

  long getId() {
    return id;
  }
}
