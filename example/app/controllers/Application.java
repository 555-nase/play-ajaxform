package controllers;

import play.*;
import play.data.Form;
import static play.data.Form.form;
import play.mvc.*;

import views.html.*;

import models.TestMOdel;

public class Application extends Controller {
  
    public static Result index() {
        Form<TestMOdel> testForm = form(TestMOdel.class);
        return ok(index.render("Your new application is ready.", testForm, request().uri()));
    }

    public static Result save() {
        Form<TestMOdel> testForm = form(TestMOdel.class).bindFromRequest();
        if(testForm.hasErrors()){
            return badRequest(index.render("Your new application is ready.", testForm, form().bindFromRequest().get("uri")));
        }else{
            flash("success", "Licensed Product has been created ");
            //testForm.get().save();
        }
        return ok("/testFormSubmit");
    }

    public static Result submit() {
        return ok(test.render());
    }
}
