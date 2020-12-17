package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocType {
    private Integer typeId;
    private String typeName;
    private Integer uploadPoint;
    private State state;
}
