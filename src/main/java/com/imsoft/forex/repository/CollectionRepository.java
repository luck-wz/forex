package com.imsoft.forex.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.imsoft.forex.entity.Collection;

public interface CollectionRepository extends JpaRepository<Collection, String> {
    
    @Query(value = "SELECT * FROM COLLECTION c "
            + "      WHERE COLLECTION_DATE >= to_date(?1, 'yyyy/MM/dd')"
            + "        AND COLLECTION_DATE <= to_date(?2, 'yyyy/MM/dd')", nativeQuery = true)
    public List<Collection> findClassAttrByChidAndCaidIn(String startDate, String endDate);
}
