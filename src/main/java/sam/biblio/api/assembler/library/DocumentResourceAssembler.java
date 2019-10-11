package sam.biblio.api.assembler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.library.DocumentController;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.model.library.Lending;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DocumentResourceAssembler implements ResourceAssembler<Document, Resource<Document>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public Resource<Document> toResource(Document document) {
        Link linkToSelf = entityLinks.linkToSingleResource(Document.class, document.getId()).withSelfRel();

        return new Resource<Document>(document, linkToSelf);
        //
        //        linkTo(methodOn(DocumentController.class).one(document.getId())).withSelfRel(),
        //        linkTo(methodOn(DocumentController.class).all(null, null)).withRel("documents"));
    }

}
