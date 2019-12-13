package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import sam.biblio.api.assembler.library.LendingResourceAssembler;
import sam.biblio.api.exception.LendingPeriodExtensionException;
import sam.biblio.api.exception.ResourceNotFoundException;
import sam.biblio.api.model.library.Lending;
import sam.biblio.api.repository.library.LendingRepository;

import java.util.Optional;

/**
 * En tant que RepositoryRestController, ce contrôleur dispose des implémentations de tous les verbes HTTP.
 * En outre, la méthode PATCH est spécifique et remplace donc l'implémentation de Spring Data Rest.
 */
@RepositoryRestController
public class LendingController {

    private LendingRepository lendingRepository;

    private LendingResourceAssembler lendingResourceAssembler;

    @Autowired
    public LendingController(LendingRepository lendingRepository, LendingResourceAssembler lendingResourceAssembler) {
        this.lendingRepository = lendingRepository;
        this.lendingResourceAssembler = lendingResourceAssembler;
    }

    @PatchMapping(path = "/lendings/{id}")
    @ResponseBody
    public void patch(@PathVariable Long id, @RequestBody Lending updatedLending) {

        Optional<Lending> found = lendingRepository.findById(id);
        if (!found.isPresent())
            throw new ResourceNotFoundException("lending", id);

        Lending lending = found.get();
        if (updatedLending.getStart()!=null)
            lending.setStart(updatedLending.getStart());
        if (updatedLending.getEnd()!=null)
            lending.setEnd(updatedLending.getEnd());
        if (updatedLending.getNbPostponement()!=null) {
            if (updatedLending.getNbPostponement() > 2)
                throw new LendingPeriodExtensionException(id, updatedLending.getNbPostponement());
            lending.setNbPostponement(updatedLending.getNbPostponement());
        }
        if (updatedLending.getMember()!=null)
            lending.setMember(updatedLending.getMember());

        lendingRepository.save(lending);
    }

}
