package sam.biblio.api.assembler.library;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.library.DocumentController;
import sam.biblio.api.model.library.Document;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DocumentResourceAssembler implements ResourceAssembler<Document, Resource<Document>> {

    @Override
    public Resource<Document> toResource(Document document) {
        return new Resource<>(document,
                linkTo(methodOn(DocumentController.class).one(document.getId())).withSelfRel(),
                linkTo(methodOn(DocumentController.class).all(null, null)).withRel("documents"));
    }

}
