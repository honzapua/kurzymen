package kurzy.men.repositories;

import kurzy.men.domain.CsasExchangeReference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO objekt, ktery pracuje v entitami v databazi
 */
public interface CsasExchangeReferenceRepository extends JpaRepository<CsasExchangeReference, Long> {

}
