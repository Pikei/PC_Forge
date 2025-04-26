package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProcessorRepository extends JpaRepository<Processor, Long> {
    List<Processor> findBySocket_SocketName(String socketName);

    List<Processor> findByLine(String line);

    List<Processor> findByCores(Integer cores);

    List<Processor> findByUnlocked(Boolean unlocked);

    List<Processor> findByFrequency(Double frequency);

    @Query("select p from Processor p where (:unit is null and p.integratedGraphicsUnit is null) or (:unit is not null and p.integratedGraphicsUnit = :unit)")
    List<Processor> findByIntegratedGraphicsUnit(@Nullable @Param("unit") String integratedGraphicsUnit);

    List<Processor> findByCoolerIncluded(Boolean coolerIncluded);

    List<Processor> findByPackaging(String packaging);

    @Query("select p.socket.socketName, count(*) from Processor p group by p.socket.socketName order by p.socket.socketName")
    List<Object[]> getSocketFilter();

    @Query("select concat(p.producer, ' ', p.line), count(*) from Processor p group by concat(p.producer, ' ', p.line) order by concat(p.producer, ' ', p.line)")
    List<Object[]> getLineFilter();

    @Query("select p.cores, count(*) from Processor p group by p.cores order by p.cores")
    List<Object[]> getCoresFilter();

    @Query("select p.unlocked, count(*) from Processor p group by p.unlocked order by p.unlocked")
    List<Object[]> getUnlockedFilter();

    @Query("select p.frequency, count(*) from Processor p group by p.frequency order by p.frequency")
    List<Object[]> getFrequencyFilter();

    @Query("select p.integratedGraphicsUnit, count(*) from Processor p group by p.integratedGraphicsUnit order by p.integratedGraphicsUnit nulls first")
    List<Object[]> getIntegratedGraphicsUnitFilter();

    @Query("select p.coolerIncluded, count(*) from Processor p group by p.coolerIncluded order by p.coolerIncluded")
    List<Object[]> getCoolerIncludedFilter();

    @Query("select p.packaging, count(*) from Processor p group by p.packaging order by p.packaging")
    List<Object[]> getPackagingFilter();
}