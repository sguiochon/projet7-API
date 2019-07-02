package sam.biblio.api.assembler.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import sam.biblio.api.controller.library.LendingController;
import sam.biblio.api.model.library.Copy;
import sam.biblio.api.model.library.Lending;
import sam.biblio.api.model.library.Member;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class LendingResourceAssembler implements ResourceAssembler<Lending, Resource<Lending>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public Resource<Lending> toResource(Lending lending) {

        Link linkToSelf = entityLinks.linkToSingleResource(Lending.class, lending.getId()).withSelfRel();
        Link linkToCopy = entityLinks.linkToSingleResource(Copy.class, lending.getCopy().getCode()).withRel("copy");
        Link linkToMember = entityLinks.linkToSingleResource(Member.class, lending.getMember().getId()).withRel("member");

        return new Resource<>(lending, linkToSelf, linkToCopy, linkToMember );

    }
}
