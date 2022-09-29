package project.trut.web.Tour;

import lombok.Data;

@Data
public class TourPaging {

    private int totalCount = 0;
    private String code;
    private int pageNum = 1; //현재 페이지
    private int startPage; // 처음에 있는 페이지
    private int endPage; // 마지막에 표시되는 페이지
    private int lastPage; // 마지막 페이지
    private boolean prev;
    private boolean next;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.lastPage = (int) Math.ceil(totalCount*1.0/10);

        this.endPage = (int) Math.ceil(pageNum*1.0/10) * 10;
        this.startPage = endPage - 9;

        if (endPage > lastPage) {
            endPage = lastPage;
        }

        prev = startPage > 1;
        next = endPage < lastPage;
    }

    public boolean getPrev() {
        return prev;
    }

    public boolean getNext() {
        return next;
    }
}
