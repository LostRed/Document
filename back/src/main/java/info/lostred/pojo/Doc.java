package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    private Integer docId;
    private String docTitle;
    private DocType docType;
    private String docCover;
    private String docUrl;
    private Integer downloadTotal;
    private Integer downloadPoint;
    private User uploadUser;
    private Date uploadTime;
    private Admin admin;
    private Date auditTime;
    private State state;
    private String docDesc;
}
