package pl.com.bottega.docflowjee.docflow.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(
    indexes = {
        @Index(columnList = "number", unique = true)
    }
)
@NamedQuery(
    name = "Document.findByNumber",
    query = "FROM Document d WHERE d.number = :number"
)
@NamedQuery(
    name = "Document.countByNumber",
    query = "SELECT count(d) FROM Document d WHERE d.number = :number"
)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String number, title, content;

    @Enumerated(value = EnumType.ORDINAL)
    public DocumentStatus status;

    public Long creatingEmployee;

}
