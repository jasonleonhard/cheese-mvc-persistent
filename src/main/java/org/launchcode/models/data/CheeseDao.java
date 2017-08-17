package org.launchcode.models.data;
import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository    // allows spring to create class that impliments this interface
@Transactional // wrap methods in db transaction/queries
// CrudRepository allows database IO
// interface works with database, stores Cheese objects with Integer keys
// not sure why Cheese, Integer is value, key and not the other way around
// DAO Data Access Object
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
