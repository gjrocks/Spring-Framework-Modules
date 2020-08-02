package com.gj.ws.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;
@NameBinding
@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD })
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Logged {

}
