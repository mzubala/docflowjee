package pl.com.bottega.docflowjee.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.jboss.logging.Logger;
import pl.com.bottega.docflowjee.docflow.events.DocumentArchivedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentCreatedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentPassedToVerification;
import pl.com.bottega.docflowjee.docflow.events.DocumentPublishedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentUpdatedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentVerifiedEvent;
import pl.com.bottega.docflowjee.docflow.events.NewDocumentVersionCreatedEvent;
import pl.com.bottega.eventsourcing.Event;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/DocflowEvents"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DocflowListener implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(DocflowListener.class.toString());
    private static final String EVENT_CLASS = "EVENT_CLASS";

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private CatalogService catalogService;

    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                String eventClassName = msg.getStringProperty(EVENT_CLASS);
                String text = msg.getText();
                LOGGER.info("Event class: " + eventClassName);
                Class<?> eventClass = Try.of(() -> Class.forName(eventClassName))
                    .getOrElseThrow((e) -> new RuntimeException("Event class not found"));
                Event event = (Event) Try.of(() -> objectMapper.readValue(text, eventClass))
                    .getOrElseThrow((e) -> new RuntimeException("Failed to parse event json: " + text));
                LOGGER.info("Received Event: " + event.toString());
                process(event);
            } else {
                LOGGER.warn("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(Event event) {
        if(event instanceof DocumentCreatedEvent) {
            catalogService.process((DocumentCreatedEvent) event);
        } else if(event instanceof DocumentUpdatedEvent) {
            catalogService.process((DocumentUpdatedEvent) event);
        } else if(event instanceof DocumentPassedToVerification) {
            catalogService.process((DocumentPassedToVerification) event);
        } else if(event instanceof DocumentVerifiedEvent) {
            catalogService.process((DocumentVerifiedEvent) event);
        } else if(event instanceof DocumentPublishedEvent) {
            catalogService.process((DocumentPublishedEvent) event);
        } else if(event instanceof DocumentArchivedEvent) {
            catalogService.process((DocumentArchivedEvent) event);
        } else if(event instanceof NewDocumentVersionCreatedEvent) {
            catalogService.process((NewDocumentVersionCreatedEvent) event);
        }
    }

}
