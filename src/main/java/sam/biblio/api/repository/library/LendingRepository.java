package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Lending;

import java.time.LocalDate;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Long> {

    Page<Lending> findByEndBefore(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate limitDate, Pageable pageInfo);

}
