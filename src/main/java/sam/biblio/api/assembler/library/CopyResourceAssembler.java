package sam.biblio.api.assembler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.Document;
import sam.biblio.api.model.library.Lending;
import sam.biblio.api.model.library.Member;

import java.util.ArrayList;
import java.util.List;

@Component
public class CopyResourceAssembler implements RepresentationModelAssembler<Copy, EntityModel<Copy>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public EntityModel<Copy> toModel(Copy copy) {

        List<Link> links = new ArrayList<>();
        Link linkToSelf = entityLinks.linkForItemResource(Lending.class, copy.getId()).withSelfRel();
        links.add(linkToSelf);

        Link linkToDocument = entityLinks.linkForItemResource(Document.class, copy.getDocument().getId()).withRel("document");
        links.add(linkToDocument);

        if (copy.getLending() != null) {
            Link linkToLending = entityLinks.linkForItemResource(Lending.class, copy.getLending().getId()).withRel("lending");
            links.add(linkToLending);
        }

        return new EntityModel<>(copy, links);
    }

}
