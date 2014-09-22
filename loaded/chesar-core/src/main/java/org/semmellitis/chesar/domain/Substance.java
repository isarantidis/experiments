package org.semmellitis.chesar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.semmellitis.chesar.annotation.BusinessKey;

@Entity
public class Substance {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "substance-sec")
  @SequenceGenerator(name = "substance-sec", sequenceName = "SUBSTANCE_SEC", initialValue = 1,
      allocationSize = 12)
  private Long id;

  @BusinessKey
  @Column(unique = true)
  private String uuid;

  @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
  @JoinColumn(name = "substance_id", updatable = false, nullable = false)
  private Set<ChemicalEntity> chemicalEntities;

  @OneToMany(mappedBy = "substance", orphanRemoval = true, targetEntity = Csa.class)
  private Set<Long> csas;

  @SuppressWarnings("unused")
  private Substance() {};

  public Substance(String uuid) {
    this.uuid = uuid;
    addChemicalEntity(new ChemicalEntity(uuid));
  }

  public String getUuid() {
    return uuid;
  }

  public void addChemicalEntity(ChemicalEntity chem) {
    getChemicalEntities().add(chem);
  }

  private Set<ChemicalEntity> getChemicalEntities() {
    if (chemicalEntities == null) {
      chemicalEntities = new HashSet<ChemicalEntity>();
    }
    return chemicalEntities;
  }

  public Long getId() {
    return id;
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
    Substance rhs = (Substance) obj;
    return uuid.equals(rhs.uuid);
  }

  @Override
  public int hashCode() {
    return 31 * 1 + uuid.hashCode();
  }

}
