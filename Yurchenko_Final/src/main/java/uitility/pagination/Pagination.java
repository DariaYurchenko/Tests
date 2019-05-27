package uitility.pagination;

public final class Pagination {
    private  int recordsPerPage;
    private  int currentPage;

    public Pagination(int recordsPerPage, int currentPage) {
        this.recordsPerPage = recordsPerPage;
        this.currentPage = currentPage;
    }

    //TODO: >max amount af pages
    public  int calculateStart() throws RuntimeException {
        if(currentPage == 0) {
            throw new RuntimeException("Argument 'currentPage' is 0");
        }
         if (currentPage < 1) {
            return 0;
        }
        return currentPage * recordsPerPage - recordsPerPage;
    }

    //TODO: on 1 page more!!!
    public  int calculateNumOfPages(int rows) throws RuntimeException {
        if (recordsPerPage==0)
            throw new RuntimeException("Argument 'recordsPerPage' is 0");
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        return nOfPages;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public  int getCurrentPage() {
        return currentPage;
    }

}