package vn.amela.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class NotifiedUserId implements Serializable {

    private Long notiUserId;
    private Long userId;
}
