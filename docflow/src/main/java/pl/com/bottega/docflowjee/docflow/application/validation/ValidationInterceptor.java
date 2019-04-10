package pl.com.bottega.docflowjee.docflow.application.validation;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Interceptor
@ValidateCommand
public class ValidationInterceptor {

    @Inject
    private Validator validator;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        checkUsage(ctx);
        Object command = ctx.getParameters()[0];
        if(command == null) {
            throw new IllegalArgumentException("Null command passed to service");
        }
        Set<ConstraintViolation<Object>> errors = validator.validate(command);
        if(!errors.isEmpty()) {
            throw new ConstraintViolationException("Command is invalid", errors);
        }
        return ctx.proceed();
    }

    private void checkUsage(InvocationContext ctx) {
        Class<?>[] types = ctx.getMethod().getParameterTypes();
        if(types.length != 1) {
            throw new IllegalArgumentException("@ValidateCommand annotation can only be used on methods " +
                "that take a single argument.");
        }
    }
}