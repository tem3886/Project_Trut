package project.trut.domain.tour;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTour is a Querydsl query type for Tour
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTour extends EntityPathBase<Tour> {

    private static final long serialVersionUID = -1865306432L;

    public static final QTour tour = new QTour("tour");

    public final DatePath<java.time.LocalDate> dateTime = createDate("dateTime", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath titleA = createString("titleA");

    public final StringPath titleB = createString("titleB");

    public final StringPath titleC = createString("titleC");

    public QTour(String variable) {
        super(Tour.class, forVariable(variable));
    }

    public QTour(Path<? extends Tour> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTour(PathMetadata metadata) {
        super(Tour.class, metadata);
    }

}

