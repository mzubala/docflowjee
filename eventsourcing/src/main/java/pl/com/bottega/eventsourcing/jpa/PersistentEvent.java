package pl.com.bottega.eventsourcing.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "events", indexes = @Index(columnList = "aggregateId"))
@NamedQuery(name = "PersistentEvent.forAggregate",
    query = "SELECT e FROM PersistentEvent e WHERE e.aggregateId = :id"
)
@NamedQuery(name = "PersistentEvent.lastVersion",
    query = "SELECT MAX(e.version) FROM PersistentEvent e WHERE e.aggregateId = :id"
)
public class PersistentEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 1 << 16)
    String payload;

    String eventClass;

    UUID aggregateId;

    Instant timestamp;

    Long version;

}
