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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: sahmed
 * Date: 3/13/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */

@Named
@RequestScoped
public class MyEventsController {

    @EJB
    private EventService eventService;

    private User user;
    private FacesContext facesContext;
    private HttpSession session;
    private List<Event> eventList;

    protected final Log log = LogFactory.getLog(getClass());

    @PostConstruct
    public void startUp() {
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(false);

        user = eventService.getEventByUserId((Integer)session.getAttribute("userId"));

        if(user == null) {
            eventList = new ArrayList<Event>();
        } else {
            eventList = user.getEventList();
        }
    }

    public Integer getEventListSize() {
        return eventList.size();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
