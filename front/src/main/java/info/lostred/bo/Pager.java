package info.lostred.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager {
    private int page;
    private int totalPage;
    private int start;
    private int end;
    private int recPerPage;
    private int recTotal;

    public Pager(int page, int recPerPage, int recTotal) {
        this.page = page;
        this.recPerPage = recPerPage;
        this.recTotal = recTotal;
        setPage();
        setRow();
    }

    public void setRow() {
        this.start = (page - 1) * recPerPage;
        this.end = start + recPerPage;
    }

    public void setPage() {
        if (recTotal % recPerPage == 0 && recTotal != 0) {
            totalPage = recTotal / recPerPage;
        } else {
            totalPage = recTotal / recPerPage + 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
    }
}
