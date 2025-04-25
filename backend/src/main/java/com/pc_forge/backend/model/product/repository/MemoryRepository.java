package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findByMemoryType(String memoryType);

    List<Memory> findByTotalCapacity(Integer totalCapacity);

    List<Memory> findByFrequency(Integer frequency);

    List<Memory> findByNumberOfModules(Integer numberOfModules);

    List<Memory> findByLatency(String latency);

    List<Memory> findByLighting(Boolean lighting);

    @Query("select m.memoryType, count(*) from Memory m group by m.memoryType order by m.memoryType desc")
    List<Object[]> getMemoryTypeFilter();

    @Query("select m.totalCapacity, count(*) from Memory m group by m.totalCapacity order by m.totalCapacity desc")
    List<Object[]> getTotalCapacityFilter();

    @Query("select m.frequency, count(*) from Memory m group by m.frequency order by m.frequency desc")
    List<Object[]> getFrequencyFilter();

    @Query("select m.numberOfModules, count(*) from Memory m group by m.numberOfModules order by m.numberOfModules desc")
    List<Object[]> getNumberOfModulesFilter();

    @Query("select m.latency, count(*) from Memory m group by m.latency order by m.latency desc")
    List<Object[]> getLatencyFilter();

    @Query("select m.lighting, count(*) from Memory m group by m.lighting order by m.lighting desc")
    List<Object[]> getLightningFilter();
}