package controller;

import entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
 * Date: 3/16/13
 * Time: 2:22 PM
 */

@Named
@RequestScoped
public class MyContactsController {

    @EJB
    private UserService userService;

    private User user;
    private FacesContext facesContext;
    private HttpSession session;
    private List<User> contactList;
    private List<User> userList;
    private Integer userId;

    protected final Log log = LogFactory.getLog(getClass());

    @PostConstruct
    public void startUp() {
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(true);

        user = userService.getUserWithContactList((Integer) session.getAttribute("userId"));
        userId = user.getId();
        contactList = user.getContactList();
    }

    public List<User> complete(String query) {
        return userService.getUserForAutoComplete(userId, query);
    }

    public void addContacts() {

    }

    public List<User> getContactList() {
        return contactList;
    }

    public void setContactList(List<User> contactList) {
        this.contactList = contactList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
