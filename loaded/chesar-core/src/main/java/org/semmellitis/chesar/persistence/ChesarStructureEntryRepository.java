package org.semmellitis.chesar.persistence;

import org.semmellitis.chesar.domain.ChesarStructureEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChesarStructureEntryRepository extends
    JpaRepository<ChesarStructureEntry, ChesarStructureEntry.ChesarStructureEntryID> {

}
