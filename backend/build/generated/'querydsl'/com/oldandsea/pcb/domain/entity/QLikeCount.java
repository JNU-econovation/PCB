package com.oldandsea.pcb.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikeCount is a Querydsl query type for LikeCount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikeCount extends EntityPathBase<LikeCount> {

    private static final long serialVersionUID = 1586609916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikeCount likeCount = new QLikeCount("likeCount");

    public final QComment comment;

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final QMember member;

    public QLikeCount(String variable) {
        this(LikeCount.class, forVariable(variable), INITS);
    }

    public QLikeCount(Path<? extends LikeCount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikeCount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikeCount(PathMetadata metadata, PathInits inits) {
        this(LikeCount.class, metadata, inits);
    }

    public QLikeCount(Class<? extends LikeCount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QComment(forProperty("comment"), inits.get("comment")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

