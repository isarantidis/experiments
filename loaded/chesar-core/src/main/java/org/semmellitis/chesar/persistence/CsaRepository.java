package org.semmellitis.chesar.persistence;

import java.util.List;

import org.semmellitis.chesar.domain.Csa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsaRepository extends JpaRepository<Csa, Long> {

  List<Csa> findBySubstanceId(long id);

}
