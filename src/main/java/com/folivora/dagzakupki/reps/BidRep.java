package com.folivora.dagzakupki.reps;

import com.folivora.dagzakupki.domain.Bid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRep extends CrudRepository<Bid, Long> {
    List<Bid> findByObject(String object);
    List<Bid> findByObjectLike(String object);
}
