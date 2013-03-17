package converter;

import entities.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created with IntelliJ IDEA.
 * Author: sahmed
 * Date: 3/16/13
 * Time: 5:08 PM
 */

@FacesConverter("userAutoCompleteConverter")
public class UserAutoCompleteConverter implements Converter {

    protected final Log log = LogFactory.getLog(getClass());
    private boolean validationStatus;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String submittedValue) {
        validationStatus = FacesContext.getCurrentInstance().isValidationFailed();

        System.out.println("UserAutoCompleteConverter-getAsObject: " + submittedValue);
        System.out.println("UserAutoCompleteConverter-validationStatus: " + validationStatus);

        if (!validationStatus) {
            return null;
        } else {
            User user = new User();
            user.setEmail(submittedValue);
            return user;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((User) value).getEmail());
        }
    }
}
