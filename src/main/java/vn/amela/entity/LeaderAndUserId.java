package vn.amela.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LeaderAndUserId implements Serializable {

    private Long leaderId;
    private Long userId;
}