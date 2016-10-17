package tn.esprit.sigma.witnessbook.resources;

import tn.esprit.sigma.witnessbook.entities.Event;
import tn.esprit.sigma.witnessbook.service.EventService;
import tn.esprit.sigma.witnessbook.resources.util.HeaderUtil;
import tn.esprit.sigma.witnessbook.security.Secured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing Event.
 */
@Path("/api/event")
@Secured
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    @Inject
    private EventService eventService;

    /**
     * POST : Create a new event.
     *
     * @param event the event to create
     * @return the Response with status 201 (Created) and with body the new
     * event, or with status 400 (Bad Request) if the event has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createEvent(Event event) throws URISyntaxException {
        log.debug("REST request to save Event : {}", event);
        eventService.create(event);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/event/" + event.getId())),
                "event", event.getId().toString())
                .entity(event).build();
    }

    /**
     * PUT : Updates an existing event.
     *
     * @param event the event to update
     * @return the Response with status 200 (OK) and with body the updated
     * event, or with status 400 (Bad Request) if the event is not valid, or
     * with status 500 (Internal Server Error) if the event couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateEvent(Event event) throws URISyntaxException {
        log.debug("REST request to update Event : {}", event);
        eventService.edit(event);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "event", event.getId().toString())
                .entity(event).build();
    }

    /**
     * GET : get all the events. <% if (pagination != 'no') {} @param pageable
     * the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of events in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Event> getAllEvents() {
        log.debug("REST request to get all Events");
        List<Event> events = eventService.findAll();
        return events;
    }

    /**
     * GET /:id : get the "id" event.
     *
     * @param id the id of the event to retrieve
     * @return the Response with status 200 (OK) and with body the event, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getEvent(@PathParam("id") Integer id) {
        log.debug("REST request to get Event : {}", id);
        Event event = eventService.find(id);
        return Optional.ofNullable(event)
                .map(result -> Response.status(Response.Status.OK).entity(event).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" event.
     *
     * @param id the id of the event to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeEvent(@PathParam("id") Integer id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.remove(eventService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "event", id.toString()).build();
    }

}
