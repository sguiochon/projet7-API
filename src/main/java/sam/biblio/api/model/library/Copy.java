package sam.biblio.api.model.library;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Copy {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("copyId")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CopyStatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Document document;

    @OneToOne(mappedBy = "copy")
    private Lending lending;

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CopyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CopyStatusEnum status) {
        this.status = status;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
