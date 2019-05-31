package uitility.pagination;

import exception.PaginationException;
import org.apache.log4j.Logger;

public final class Pagination {
    private static final Logger LOGGER = Logger.getLogger(Pagination.class);

    private  int recordsPerPage;
    private  int currentPage;

    public Pagination(int recordsPerPage, int currentPage) {
        this.recordsPerPage = recordsPerPage;
        this.currentPage = currentPage;
    }

    public  int calculateStart(int noOfPages) {
         if (currentPage < 1 || currentPage > noOfPages) {
            return 0;
         }
        return currentPage * recordsPerPage - recordsPerPage;
    }

    public int calculateNumOfPages(int rows) {
        if (recordsPerPage==0) {
            LOGGER.warn("Records per page = 0 while pagination.");
            throw new PaginationException();
        }
        int nOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
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
