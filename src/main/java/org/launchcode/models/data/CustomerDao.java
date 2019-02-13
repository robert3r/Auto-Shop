package org.launchcode.models.data;

import org.launchcode.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
    public interface CustomerDao extends CrudRepository<Customer, Integer> {
}

