package sam.biblio.api.controller.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;
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
    public LendingController(LendingRepository lendingRepository, LendingResourceAssembler lendingResourceAssembler){
        this.lendingRepository = lendingRepository;
        this.lendingResourceAssembler = lendingResourceAssembler;
    }

    @PatchMapping(path = "/lendings/{id}")
    @ResponseBody
    public Resource<Lending> patch(@PathVariable Long id, @RequestBody Lending updatedLending) {

        Lending lending = lendingRepository.findById(id)
                .map( l -> {
                    if (l.getNbPostponement()>=2)
                        throw new LendingPeriodExtensionException( id, l.getNbPostponement());
                  return l;
                } )
                .map(l -> {
                    l.setStart(updatedLending.getStart());
                    l.setEnd(updatedLending.getEnd());
                    l.setNbPostponement(updatedLending.getNbPostponement());
                    return lendingRepository.save(l);
                })
                .orElseThrow(() -> new ResourceNotFoundException("lending", id));

        return lendingResourceAssembler.toResource(lending);
    }

}
