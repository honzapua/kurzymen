package kurzy.men.annotation;

import java.lang.annotation.*;

/**
 * Standartnni dle JavaDocu, nad kterymi prvky lze pouzit
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
@Documented  // napise se do dokumentace, ze je k udelani
public @interface Remove {

    String value() default "";

}
