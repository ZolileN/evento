package controller;

import entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private List<String> userList;
    private Integer userId;
    private String autoCompleteUserEmail;
//  private User autoCompleteUser;

    protected final Log log = LogFactory.getLog(getClass());

    @PostConstruct
    public void startUp() {
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(false);

        userId = (Integer) session.getAttribute("userId");
        user = userService.getUserWithContactList(userId);

        System.out.println("MyContactsController-userId: " + session.getAttribute("userId"));
        System.out.println("MyContactsController-user: " + user);

        if (user != null) {
            contactList = user.getContactList();
        } else {
            contactList = new ArrayList<User>();
        }
    }

    public List<String> complete(String query) {
        List<User> users = userService.getUserForAutoComplete(userId, query);
        userList = new ArrayList<String>();

        for (User user : users) {
            userList.add(user.getEmail());
        }

        System.out.println("MyContactsController-complete: " + userList.size());
        return userList;
    }

    public String addContacts() {
        System.out.println("MyContactsController-addContacts: " + autoCompleteUserEmail);

        boolean result = isUserInContactList(autoCompleteUserEmail);

        if (result) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "User Present in Contact List", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

            return "";
        } else {
            userService.addFriend(userId, autoCompleteUserEmail);

            return "my_contacts.xhtml?faces-redirect=true";
        }
    }

    public boolean isUserInContactList(String email) {
        for (User contact : contactList) {
            if (contact.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getContactList() {
        return contactList;
    }

    public void setContactList(List<User> contactList) {
        this.contactList = contactList;
    }

//    public User getAutoCompleteUser() {
//        return autoCompleteUser;
//    }
//
//    public void setAutoCompleteUser(User autoCompleteUser) {
//        this.autoCompleteUser = autoCompleteUser;
//    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public String getAutoCompleteUserEmail() {
        return autoCompleteUserEmail;
    }

    public void setAutoCompleteUserEmail(String autoCompleteUserEmail) {
        this.autoCompleteUserEmail = autoCompleteUserEmail;
    }
}
