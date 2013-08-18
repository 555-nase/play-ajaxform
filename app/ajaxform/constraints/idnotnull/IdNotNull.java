package ajaxform.constraints.idnotnull;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.*;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IdNotNullValidator.class)
@play.data.Form.Display(name="constraint.idnotnull")
public @interface IdNotNull {
    String message() default IdNotNullValidator.message;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}