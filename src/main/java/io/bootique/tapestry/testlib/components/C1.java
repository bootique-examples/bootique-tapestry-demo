package io.bootique.tapestry.testlib.components;

import io.bootique.tapestry.demo.pages.HelloPage;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;

public class C1 {

    @Property
    private String userName;

    @Property
    private String password;

    @InjectComponent("password")
    private PasswordField passwordField;

    @Component
    private Form loginForm;

    @InjectPage
    private HelloPage postLogin;

    void onValidateFromLoginForm() {
        //       if (!authenticator.isValid(userName, password)) {
        if (false) {
            // record an error, and thereby prevent Tapestry from emitting a "success" event
            loginForm.recordError(passwordField, "Invalid user name or password.");
        }
        //     }
    }

    Object onSuccess() {
        postLogin.set(userName);
        return postLogin;
    }

    public String getVal() {
        return "Please sign in";
    }
}
