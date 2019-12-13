package sam.biblio.api.assembler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.Lending;
import sam.biblio.api.model.library.Member;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class LendingResourceAssembler implements RepresentationModelAssembler<Lending, EntityModel<Lending>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public EntityModel<Lending> toModel(Lending lending) {

        Collection<Link> links = new ArrayList<>();
        Link linkToSelf = entityLinks.linkForItemResource(Lending.class, lending.getId()).withSelfRel();
        links.add(linkToSelf);

        Link linkToCopy = null;
        if (lending.getCopy()!=null){
            entityLinks.linkForItemResource(Copy.class, lending.getCopy().getId()).withRel("copy");
            links.add(linkToCopy);
        }

        Link linkToMember = null;
        if (lending.getMember() != null) {
            linkToMember = entityLinks.linkForItemResource(Member.class, lending.getMember().getId()).withRel("member");
            links.add(linkToMember);
        }

        return new EntityModel<>(lending, links);

    }
}
