import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Objects with this annotation should be serialized
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface XMLable {}