package com.db.repository;

import com.db.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

    @Query(value = "select * from Trade where tradeId = ?1 order by createdDate desc" , nativeQuery = true)
    Optional<Trade> findTradeByTradeId(String tradeId);
    // Note: casting from uuid to varchar due to jdbc error while converting uuid type
    @Query(value = "update Trade set expired='Y' where maturityDate< CURRENT_DATE()" , nativeQuery = true)
    int updateExpiryFlag();


}
