package validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created with IntelliJ IDEA.
 * Author: sahmed
 * Date: 3/17/13
 * Time: 11:45 AM
 */

@FacesValidator("userAutoCompleteValidator")
public class UserAutoCompleteValidator implements Validator {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
        System.out.println("UserAutoCompleteValidator: " + obj);
    }
}
