package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Integer logId;
    private User user;
    private Admin admin;
    private String operation;
    private Date time;
}
