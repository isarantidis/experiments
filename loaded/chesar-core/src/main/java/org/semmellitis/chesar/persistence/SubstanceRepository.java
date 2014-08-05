package org.semmellitis.chesar.persistence;

import org.semmellitis.chesar.domain.Substance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, String> {

  Substance findByUuid(String uuid);

}
