package org.semmellitis.chesar.domain;

import java.io.Serializable;

public class CsaId implements Serializable {
  public Long id;

  public CsaId() {}

  public CsaId(Long id) {
    this.id = id;
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
    CsaId rhs = (CsaId) obj;
    return id.equals(rhs.id);
  }

  @Override
  public int hashCode() {
    return 31 * 1 + id.intValue();
  }
}
