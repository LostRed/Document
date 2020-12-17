package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRecord {
    private Integer recordId;
    private User user;
    private Doc doc;
    private Date downloadTime;
}
