package info.lostred.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resp {
    private Integer errCode;
    private String errMsg;
    private Object data;
}
