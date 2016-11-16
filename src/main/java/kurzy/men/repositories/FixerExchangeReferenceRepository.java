package kurzy.men.repositories;

import kurzy.men.domain.FixerExchangeReference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO objekt, ktery pracuje v entitami v databazi
 */
public interface FixerExchangeReferenceRepository extends JpaRepository<FixerExchangeReference, Long> {

}
