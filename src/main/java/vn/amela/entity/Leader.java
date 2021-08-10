package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity(name = "leader_user")
@NoArgsConstructor
public class Leader {

    @EmbeddedId
    private LeaderId leaderId;

    public Leader(Long leaderId, Long userId) {
        this.leaderId.setLeaderId(leaderId);
        this.leaderId.setUserId(userId);
    }
}
