package gosk.szymon.dev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD})
public @interface DevOnly { }
