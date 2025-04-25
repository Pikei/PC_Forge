package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    List<Motherboard> findBySocket_Socket(String socket);

    List<Motherboard> findByStandard_StandardName(String standardName);

    List<Motherboard> findByChipset(String chipset);

    List<Motherboard> findByMemoryStandard(String memoryStandard);

    List<Motherboard> findByMemorySlots(Integer memorySlots);

    List<Motherboard> findByMaxMemoryCapacity(Integer maxMemoryCapacity);

    @Query(value = "select * from motherboard as mb join product as prod on mb.ean = prod.ean where supported_memory_frequencies @> array[?1]", nativeQuery = true)
    List<Motherboard> findBySupportedMemoryFrequency(Integer frequency);

    List<Motherboard> findByBluetooth(Boolean bluetooth);

    List<Motherboard> findByWifi(Boolean wifi);

    List<Motherboard> findByWidthBetween(Double widthStart, Double widthEnd);

    List<Motherboard> findByDepthBetween(Double depthStart, Double depthEnd);

    @Query("select m.socket.socket, count(*) from Motherboard m group by m.socket.socket order by m.socket.socket")
    List<Object[]> getSocketFilter();

    @Query("select m.standard.standardName, count(*) from Motherboard m group by m.standard.standardName order by m.standard.standardName")
    List<Object[]> getStandardFilter();

    @Query("select m.chipset, count(*) from Motherboard m group by m.chipset order by m.chipset")
    List<Object[]> getChipsetFilter();

    @Query("select m.memoryStandard, count(*) from Motherboard m group by m.memoryStandard order by m.memoryStandard")
    List<Object[]> getMemoryStandardFilter();

    @Query("select m.memorySlots, count(*) from Motherboard m group by m.memorySlots order by m.memorySlots")
    List<Object[]> getMemorySlotFilter();

    @Query(value = "select distinct unnest(supported_memory_frequencies) as frequency, count(*) from motherboard group by frequency order by frequency", nativeQuery = true)
    List<Object[]> getFrequencyFilter();

    @Query("select m.bluetooth, count(*) from Motherboard m group by m.bluetooth order by m.bluetooth")
    List<Object[]> getBluetoothFilter();

    @Query("select m.wifi, count(*) from Motherboard m group by m.wifi order by m.wifi")
    List<Object[]> getWiFiFilter();

    @Query("select m.maxMemoryCapacity, count(*) from Motherboard m group by m.maxMemoryCapacity order by m.maxMemoryCapacity")
    List<Object[]> getMaxMemoryFilter();

    @Query("select m.width from Motherboard m order by m.width asc limit 1")
    Double getMinWidth();

    @Query("select m.width from Motherboard m order by m.width desc limit 1")
    Double getMaxWidth();

    @Query("select m.depth from Motherboard m order by m.depth asc limit 1")
    Double getMinDepth();

    @Query("select m.depth from Motherboard m order by m.depth desc limit 1")
    Double getMaxDepth();

}