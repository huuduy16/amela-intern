package vn.amela.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class NotifyUserId implements Serializable {

    private Long notiUserId;
    private Long userId;
}
