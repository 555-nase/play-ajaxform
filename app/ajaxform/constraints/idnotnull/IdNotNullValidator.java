package ajaxform.constraints.idnotnull;

import javax.validation.*;


import play.libs.F.Tuple;

public class IdNotNullValidator extends play.data.validation.Constraints.Validator<Object> implements ConstraintValidator<IdNotNull, Object> {

    /* Default error message */
    final static public String message = "error.idnotnull";

    /**
     * Validator init
     */
    public void initialize(IdNotNull constraintAnnotation) {}

    /**
     * The validation itself
     */
    public boolean isValid(Object object) {
    	IdNotNullInterface idnotnull = (IdNotNullInterface) object;
        if(idnotnull.getId() == null){
            return false;
        }
        return true;
    }

    /**
     * Constructs a validator instance.
     */
    public static play.data.validation.Constraints.Validator<Object> notnull() {
        return new IdNotNullValidator();
    }

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
        return new Tuple<String, Object[]>(message, new Object[] {});
    } 
}