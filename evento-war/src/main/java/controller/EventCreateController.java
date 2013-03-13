package controller;

import entities.Event;
import entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.EventService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sahmed
 * Date: 2/13/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class EventCreateController {

    private Event event;
    private User user;
    private Integer eventId;
    private FacesContext facesContext;
    private HttpSession session;

    protected final Log log = LogFactory.getLog(getClass());

    @EJB
    private EventService eventService;

    @EJB
    private UserService userService;

    @PostConstruct
    public void startUp() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (params.get("eventId") != null) {
            eventId = Integer.valueOf(params.get("eventId"));
            event = eventService.getEventById(eventId);
            System.out.println("startUp-Event-with=param: " + event);
        } else {
            event = new Event();
            log.info("startUp-Event: " + event);
            System.out.println("startUp-Event: " + event);
        }

        if (user == null) {
            facesContext = FacesContext.getCurrentInstance();
            session = (HttpSession) facesContext.getExternalContext().getSession(true);
            user = userService.getUserById((Integer) session.getAttribute("userId"));
            log.info("eventCreate: " + user);
        }
    }

    public String createEvent() {
        System.out.println(("createEvent: " + event));
        if (event != null) {
            Integer eventId = eventService.createNewEvent(user.getId(), event);
            System.out.println("createEvent-eventId: " + eventId);
            return "event_page.xhtml?eventId=" + eventId + "&faces-redirect=true";
        }
        return "event_create.xhtml?faces-redirect=true";
    }

    public String updateEvent() {
        if (event != null) {
            eventService.updateEventInfo(event);
            return "event_page.xhtml?eventId=" + eventId + "&faces-redirect=true";
        } else {
            return "event_create.xhtml";
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
