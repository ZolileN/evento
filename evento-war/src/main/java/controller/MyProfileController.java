package controller;

import entities.User;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: sahmed
 * Date: 3/13/13
 * Time: 4:39 PM
 */

@Named
@RequestScoped
public class MyProfileController {

    @EJB
    private UserService userService;

    private User user;
    private Integer userId;
    private FacesContext facesContext;
    private HttpSession session;

    @PostConstruct
    public void startUp() {
        facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession(false);

        userId = (Integer) session.getAttribute("userId");
        user = userService.getUserById(userId);
    }

    public String updateProfile() {
        userService.updateUserInfo(user);
        return "my_profile.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
