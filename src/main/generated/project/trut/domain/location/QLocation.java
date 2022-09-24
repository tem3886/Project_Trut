package project.trut.domain.location;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = 1317071456L;

    public static final QLocation location = new QLocation("location");

    public final DatePath<java.time.LocalDate> dateTime = createDate("dateTime", java.time.LocalDate.class);

    public final StringPath departure = createString("departure");

    public final StringPath destination = createString("destination");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QLocation(String variable) {
        super(Location.class, forVariable(variable));
    }

    public QLocation(Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation(PathMetadata metadata) {
        super(Location.class, metadata);
    }

}

