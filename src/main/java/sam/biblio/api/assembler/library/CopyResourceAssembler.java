package sam.biblio.api.assembler.library;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.library.CopyController;
import sam.biblio.api.model.library.Copy;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CopyResourceAssembler implements ResourceAssembler<Copy, Resource<Copy>> {

    @Override
    public Resource<Copy> toResource(Copy copy) {
        return new Resource<>(copy,
                linkTo(methodOn(CopyController.class).one(copy.getId())).withSelfRel(),
                linkTo(methodOn(CopyController.class).all(null, null)).withRel("copies"));
    }


}
