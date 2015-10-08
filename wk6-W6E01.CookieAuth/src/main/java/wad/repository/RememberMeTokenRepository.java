package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.CustomPersistentToken;

public interface RememberMeTokenRepository extends JpaRepository<CustomPersistentToken, Long>{
	    List<CustomPersistentToken> findBySeries(String series);
	    List<CustomPersistentToken> findByUsername(String username);
	}