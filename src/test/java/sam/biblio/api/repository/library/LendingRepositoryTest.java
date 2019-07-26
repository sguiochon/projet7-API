package sam.biblio.api.repository.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sam.biblio.api.model.library.Lending;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LendingRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LendingRepository lendingRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // ARRANGE
        Lending lending = new Lending();
        lending.setStart(LocalDate.of(2019, 7, 14));
        lending.setEnd(LocalDate.of(2019, 8, 15));
        entityManager.persist(lending);
        entityManager.flush();

        // ACT
        Optional<Lending> found = lendingRepository.findById(2L);

        // ASSERT
        assertThat(found.isPresent());
    }

}
