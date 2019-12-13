package sam.biblio.api.repository.library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import sam.biblio.api.model.library.Lending;

import java.time.LocalDate;
import java.util.Optional;

@RepositoryRestResource
public interface LendingRepository extends JpaRepository<Lending, Long> {

    Page<Lending> findByEndBefore(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate limitDate, Pageable pageInfo);

    @Query(value="SELECT l FROM Lending l, Member m, User u WHERE l.member=m AND m.user=u AND u.email=:email")
    Page<Lending> findAllByUserEmail(String email, Pageable pageInfo);

    @Override
    Optional<Lending> findById(Long id);

}
