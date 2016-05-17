package ar.edu.itba.webapp.form.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.interfaces.UserService;
import ar.edu.itba.webapp.form.UserForm;

public class UserNameFreeValidator implements ConstraintValidator<UserNameFree, UserForm> {
	
	@Autowired
    private UserService us;

	private String markedFieldName;
	
    @Override
    public void initialize(final UserNameFree constraintAnnotation) {
    	markedFieldName = constraintAnnotation.markedField();
    }

    @Override
    public boolean isValid(final UserForm form, final ConstraintValidatorContext context) {
  		if (us.usernameExists(form.getUser())) {
    		context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(markedFieldName).addConstraintViolation();
    		return false;
    	} else {
    		return true;
    	}
    }

}