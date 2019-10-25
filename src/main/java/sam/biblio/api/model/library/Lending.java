package sam.biblio.api.model.library;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import sam.biblio.api.model.UrlIdentifiedRessource;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Lending {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("lendingId")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate start;

    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate end;

    private Integer nbPostponement;

    @ManyToOne
    @JoinTable(name="member_lendings",
                joinColumns = {@JoinColumn(name="lending_id",referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name="member_id",referencedColumnName = "id")})
    private Member member;

    @OneToOne
    private Copy copy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getNbPostponement() {
        return nbPostponement;
    }

    public void setNbPostponement(Integer nbPostponement) {
        this.nbPostponement = nbPostponement;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

}
