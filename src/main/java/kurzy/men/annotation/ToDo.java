package kurzy.men.annotation;

import java.lang.annotation.*;

/**
 * Anotace Todo oznacuje nedodelky a k cemu je potreba se v budoucnu vratit
 */
// Standartnni dle JavaDocu, nad kterymi prvky lze pouzit
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
public @interface ToDo {

    String value();

}
