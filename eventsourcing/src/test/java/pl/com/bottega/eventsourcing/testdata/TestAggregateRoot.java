package pl.com.bottega.eventsourcing.testdata;

import pl.com.bottega.eventsourcing.AggregateRoot;
import pl.com.bottega.eventsourcing.Event;

import java.time.Clock;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.run;
import static io.vavr.Predicates.instanceOf;

public class TestAggregateRoot extends AggregateRoot {

    private String testValue = "initial";

    TestAggregateRoot() {}

    public TestAggregateRoot(UUID id, Clock clock) {
        applyChange(new TestAggregateCreatedEvent(id, clock.instant()));
    }

    public void changeState(String newState, Clock clock) {
        if(newState == null || newState.equals(testValue)) {
            throw new IllegalArgumentException();
        }
        applyChange(new TestValueChangedEvent(this.id, clock.instant(), newState));
    }

    private void apply(TestAggregateCreatedEvent event) {
        this.id = event.getAggregateId();
    }

    private void apply(TestValueChangedEvent event) {
        this.testValue = event.getValue();
    }

    public String getState() {
        return testValue;
    }
}
