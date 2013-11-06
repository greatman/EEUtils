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
public @interface ConfigField {

    /**
     * Name of the field to be put into the Prop file
     *
     * @return The name for the field
     */
    String name() default "";

    /**
     * Extra Info to be given to the field ie. usage
     *
     * @return A String array if comments if any
     */
    String[] comments() default "";

    /**
     * What to delimit an array type if needed
     *
     * @return The delimiter if needed
     */
    String spacer() default "";

}
