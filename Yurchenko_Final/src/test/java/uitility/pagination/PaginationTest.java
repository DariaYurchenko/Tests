package uitility.pagination;

import exception.PaginationRuntimeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaginationTest {
    private static final int CURRENT_PAGE = 2;
    private static final int RECORDS_PER_PAGE = 5;
    private static final int NUMBER_OF_PAGES = 5;

    private Pagination pagination;

    @Before
    public void setUp(){
        this.pagination = new Pagination(RECORDS_PER_PAGE, CURRENT_PAGE);
    }

    @Test
    public void shouldCalculateStart(){
        int expected = 5;
        int actual = pagination.calculateStart(NUMBER_OF_PAGES);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateStartIfIncorrectPage(){
        this.pagination = new Pagination(RECORDS_PER_PAGE, -10);
        int expected1 = 0;
        int actual1 = pagination.calculateStart(NUMBER_OF_PAGES);
        assertEquals(expected1, actual1);

        this.pagination = new Pagination(RECORDS_PER_PAGE, 10);
        int expected2 = 0;
        int actual2 = pagination.calculateStart(NUMBER_OF_PAGES);
        assertEquals(expected2, actual2);
    }

    @Test
    public void shouldCalculateNumberOfPages() {
        int rows = 11;

        int expected = 3;
        int actual = pagination.calculateNumOfPages(rows);
        assertEquals(expected, actual);
    }

    @Test (expected = PaginationRuntimeException.class)
    public void shouldCalculateNumberOfPagesAndThrowException() throws PaginationRuntimeException {
        int rows = 11;
        int recordsPerPage = 0;

        this.pagination = new Pagination(recordsPerPage, CURRENT_PAGE);
        pagination.calculateNumOfPages(rows);
    }


}