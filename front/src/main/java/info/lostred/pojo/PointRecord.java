package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointRecord {
    private Integer recordId;
    private User user;
    private String name;
    private Integer point;
    private String type;
    private Date time;
}
