package practice.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditingFileds is a Querydsl query type for AuditingFileds
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditingFileds extends EntityPathBase<AuditingFileds> {

    private static final long serialVersionUID = -1231895711L;

    public static final QAuditingFileds auditingFileds = new QAuditingFileds("auditingFileds");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QAuditingFileds(String variable) {
        super(AuditingFileds.class, forVariable(variable));
    }

    public QAuditingFileds(Path<? extends AuditingFileds> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditingFileds(PathMetadata metadata) {
        super(AuditingFileds.class, metadata);
    }

}

