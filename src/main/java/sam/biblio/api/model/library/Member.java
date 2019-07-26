package sam.biblio.api.model.library;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;
import sam.biblio.api.model.UrlIdentifiedRessource;
import sam.biblio.api.model.security.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="member")
public class Member extends UrlIdentifiedRessource {


    @OneToOne
    private User user;

    private LocalDate membershipStartDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("member")
    //@JoinTable(name="member_lending",
    //        joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "id")},
    //        inverseJoinColumns = {@JoinColumn(name="lending_id",referencedColumnName = "id")})
    private Set<Lending> lendings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Set<Lending> getLendings() {
        return lendings;
    }

    public void setLendings(Set<Lending> lendings) {
        this.lendings = lendings;
    }

    public Member(String url){
        super("/members/", url);
    }

    public Member(){
        super();
    }

}
