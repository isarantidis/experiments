package org.semmellitis.chesar.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "EntityHierarchy")
public class ChesarStructureEntry {


  @EmbeddedId
  private ChesarStructureEntryID id;

  @SuppressWarnings("unused")
  private ChesarStructureEntry() {}

  public ChesarStructureEntry(long parent, String parentType, long child, String childType) {
    this.id = new ChesarStructureEntryID(parent, parentType, child, childType);
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
    ChesarStructureEntry rhs = (ChesarStructureEntry) obj;
    return id.equals(rhs.id);
  }

  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = prime * result + id.hashCode();
    return result;
  }

  @Embeddable
  public static class ChesarStructureEntryID implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1766940242337692761L;

    private long parentId;
    private String parentType;
    private long childId;
    private String childType;

    @SuppressWarnings("unused")
    public ChesarStructureEntryID() {}

    public ChesarStructureEntryID(long parent, String parentType, long child, String childType) {
      this.parentId = parent;
      this.parentType = parentType;
      this.childId = child;
      this.childType = childType;
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
      ChesarStructureEntryID rhs = (ChesarStructureEntryID) obj;
      return parentId == rhs.parentId && parentType.equals(rhs.parentType)
          && childId == rhs.childId && childType.equals(rhs.childType);
    }

    @Override
    public int hashCode() {
      int prime = 31;
      int result = 1;
      result = (int) (prime * result + parentId);
      result = prime * result + parentType.hashCode();
      result = (int) (prime * result + childId);
      result = prime * result + childType.hashCode();
      return result;
    }

  }

}
