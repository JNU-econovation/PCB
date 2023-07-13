package com.oldandsea.pcb.domain.dto.layer;

import com.oldandsea.pcb.domain.entity.Session;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLogout {
    private Session session;
    @Builder
    public MemberLogout(Session session) {
        this.session = session;
    }
}
