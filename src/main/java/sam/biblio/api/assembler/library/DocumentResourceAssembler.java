package sam.biblio.api.assembler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.model.library.Document;

@Component
public class DocumentResourceAssembler implements RepresentationModelAssembler<Document, EntityModel<Document>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public EntityModel<Document> toModel(Document document) {
        Link linkToSelf = entityLinks.linkForItemResource(Document.class, document.getId()).withSelfRel();

        return new EntityModel<Document>(document, linkToSelf);
        //
        //        linkTo(methodOn(DocumentController.class).one(document.getId())).withSelfRel(),
        //        linkTo(methodOn(DocumentController.class).all(null, null)).withRel("documents"));
    }

}
