package pl.com.bottega.docflowjee.docflow.application.validation;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@Interceptor
@ValidateCommand
public class ValidationInterceptor {

    @Inject
    private Validator validator;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        return ctx.proceed();
    }

}
