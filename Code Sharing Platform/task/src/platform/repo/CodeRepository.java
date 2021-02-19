package platform.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.model.Code;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    List<Code> findTop10ByOrderByIdDesc();

    List<Code> findTop10ByViewsRestrictFalseAndTimeRestrictFalseOrderByIdDesc();

    Optional<Code> findCodeByUUID(String uuid);

}
