package xyz.andrewkboyd.etltemplate.dao.postgresql;

import org.springframework.transaction.annotation.Transactional;
import xyz.andrewkboyd.etltemplate.dao.interfaces.LatestNumbersDAO;
import xyz.andrewkboyd.etltemplate.entities.postgresql.TestNumbers;


public class LatestNumbersImpl extends BaseDaoImpl<TestNumbers> implements LatestNumbersDAO {

    /**
     * Get the latest number from the store
     *
     * @return {int}
     */
    @Override
    @Transactional
    public int getLatestNumber() {
        var session = getSession();
        var cb = session.getCriteriaBuilder();
        var query = cb.createQuery(TestNumbers.class);
        var root = query.from(TestNumbers.class);
        query.orderBy(cb.desc(root.get("created")));

        return session.createQuery(query)
                .getFirstResult();
    }

    /**
     * Add a new number to the store
     *
     * @param number new test number
     */
    @Override
    @Transactional
    public void insertNewNumber(int number) {
        var numberRecord = new TestNumbers();
        numberRecord.setValue(number);
        save(numberRecord);
    }
}
