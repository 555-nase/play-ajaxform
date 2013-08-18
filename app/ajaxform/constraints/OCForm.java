package ajaxform.constraints;

import play.data.*;
import play.i18n.Messages;
import play.mvc.Http;
import play.mvc.Http.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.annotation.Annotation;

import ajaxform.constraints.idnotnull.IdNotNull;

/**
 * Helper to manage HTML form description, submission and validation.
 * @param <T>
 * @param <T>
 */
public class OCForm<T> {

	public Form<T> form;
	public Class<T> backedType;
	public Context ctx;

	public OCForm(Class<T> clazz) {
		this.backedType = clazz;
		this.form = new Form<T>(clazz);
	}
	
	public OCForm(Class<T> clazz, Context ctx) {
		this.backedType = clazz;
		this.form = new Form<T>(clazz);
		this.ctx = ctx;
	}

	public static <T> OCForm<T> form(Class<T> clazz, Context ctx) {
        return new OCForm<T>(clazz, ctx);
    }
	
	public Form<T> bindFromRequest(){
		Form<T> copyFrom = this.form.bindFromRequest();
		
		Field[] fields = this.backedType.getDeclaredFields();
		
		for(int i=0;i<fields.length;i++){
			Annotation ann = fields[i].getAnnotation(IdNotNull.class);
			if(ann!=null){
				if(copyFrom.hasErrors() && copyFrom.errors().containsKey(fields[i].getName())){
		    		copyFrom.reject(fields[i].getName()+".id", Messages.get("error.idnotnull"));
		    	}
			}
		}
		
		if(!copyFrom.hasErrors()){
			Object globalError = null;
			java.lang.reflect.Method v;
			try {
				
				v = this.backedType.getMethod("myValidate", String.class, Context.class);
				globalError = v.invoke(copyFrom.get(), "1", this.ctx);
					
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e){
				e.printStackTrace();	
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			if(globalError!=null && globalError instanceof String) {
				copyFrom.reject("", (String) globalError);
			}
		}

		return copyFrom;
	}

}