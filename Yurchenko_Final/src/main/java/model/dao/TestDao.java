package model.dao;

import model.entity.Test;
import model.entity.status.TestStatus;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TestDao extends GenericDao<Test> {

    Double setPercentOfUserPoints(ResultSet rs);

    TestStatus setTestStatus(ResultSet rs) throws SQLException;

}
