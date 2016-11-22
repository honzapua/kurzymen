package kurzy.men.annotation;

import java.lang.annotation.*;

/**
 * Anotace Remove oznacuje to, co nepatri do produkcniho kodu a ma byt odstranen
 */
@Retention(RetentionPolicy.SOURCE)
@Target({
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.LOCAL_VARIABLE,
        ElementType.METHOD,
        ElementType.PACKAGE,
        ElementType.PARAMETER,
        ElementType.TYPE,
        ElementType.TYPE_PARAMETER,
        ElementType.TYPE_USE
})
@Documented
public @interface Remove {

    String value() default "";

}
