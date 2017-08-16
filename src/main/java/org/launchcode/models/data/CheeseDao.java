package org.launchcode.models.data;
import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
// interface works with database, stores Cheese objects with Integer keys
// not sure why Cheese, Integer is value, key and not the other way around
// CrudRepository allows database IO
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
