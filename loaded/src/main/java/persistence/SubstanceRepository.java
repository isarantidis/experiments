package persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Substance;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, String> {

	Substance findByUuid(String uuid);

}
