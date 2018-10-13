package pl.com.bottega.docflowjee.docflow.events;

import pl.com.bottega.eventsourcing.Event;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class DocumentPublishedEvent extends Event {
    private Set<Long> departmentIds;
    private Integer version;


    public DocumentPublishedEvent(UUID id, Instant instant, Set<Long> departmentIds, Integer version) {
        super(id, instant);
        this.departmentIds = departmentIds;
        this.version = version;
    }

    public Set<Long> getDepartmentIds() {
        return departmentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DocumentPublishedEvent that = (DocumentPublishedEvent) o;
        return Objects.equals(departmentIds, that.departmentIds) &&
            Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentIds, version);
    }

    @Override
    public String toString() {
        return "DocumentPublishedEvent{" +
            "departmentIds=" + departmentIds +
            ", version=" + version +
            "} " + super.toString();
    }
}