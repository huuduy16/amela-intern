package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "noti_user")
@Data
@NoArgsConstructor
public class NotifiedUser {

    @EmbeddedId
    private NotifiedUserId notifiedUserId;

    public NotifiedUser(Long notiUserId, Long userId) {
        this.notifiedUserId.setNotiUserId(notiUserId);
        this.notifiedUserId.setUserId(userId);
    }
}
