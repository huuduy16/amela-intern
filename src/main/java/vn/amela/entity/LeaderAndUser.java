package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity(name = "leader_user")
@NoArgsConstructor
public class LeaderAndUser {

    @EmbeddedId
    private LeaderAndUserId leaderAndUserId;

    public LeaderAndUser(Long leaderId, Long userId) {
        this.leaderAndUserId.setLeaderId(leaderId);
        this.leaderAndUserId.setUserId(userId);
    }
}
