package vn.amela.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity(name = "noti_user")
@NoArgsConstructor
public class NotifyUser {

    @EmbeddedId
    private NotifyUserId notifyUserId;

    public NotifyUser(Long notiUserId, Long userId) {
        this.notifyUserId.setNotiUserId(notiUserId);
        this.notifyUserId.setUserId(userId);
    }
}
