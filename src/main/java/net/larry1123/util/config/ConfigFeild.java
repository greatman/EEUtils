/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Aug 13, 2013 8:27:45 AM
 */
package net.larry1123.util.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention ( RetentionPolicy.RUNTIME )
@Target ( ElementType.FIELD )
public @interface ConfigFeild {

    /**
     * TODO
     *
     * @return
     */
    String name() default "";

    /**
     * TODO
     *
     * @return
     */
    String[] comments() default "";

    /**
     * TODO
     *
     * @return
     */
    String spacer() default "";

}
