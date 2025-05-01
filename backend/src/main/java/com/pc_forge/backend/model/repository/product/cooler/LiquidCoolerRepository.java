package com.pc_forge.backend.model.repository.product.cooler;

import com.pc_forge.backend.model.entity.product.cooler.LiquidCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LiquidCoolerRepository extends JpaRepository<LiquidCooler, Long> {
    @Query("select l from LiquidCooler l join CoolerSocketCompatibility c on l.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId where s.socketName like :socket")
    List<LiquidCooler> findBySocket(@Param("socket") String socket);

    List<LiquidCooler> findByFans(Integer fans);

    List<LiquidCooler> findByFanDiameter(Integer fanDiameter);

    List<LiquidCooler> findByNoiseLevel(Double noiseLevel);

    List<LiquidCooler> findByLightning(Boolean lightning);

    List<LiquidCooler> findByCoolerSize(Integer coolerSize);


    @Query("select s.socketName, count(*) from LiquidCooler l join CoolerSocketCompatibility c on l.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId group by s.socketName order by s.socketName")
    List<Object[]> getSocketFiler();

    @Query("select l.fans, count(*) from LiquidCooler l group by l.fans order by l.fans")
    List<Object[]> getNumOfFansFiler();

    @Query("select l.fanDiameter, count(*) from LiquidCooler l group by l.fanDiameter order by l.fanDiameter")
    List<Object[]> getFanDiameterFiler();

    @Query("select l.noiseLevel, count(*) from LiquidCooler l group by l.noiseLevel order by l.noiseLevel")
    List<Object[]> getNoiseLvlFiler();

    @Query("select l.lightning, count(*) from LiquidCooler l group by l.lightning order by l.lightning")
    List<Object[]> getLightningFiler();

    @Query("select l.coolerSize, count(*) from LiquidCooler l group by l.coolerSize order by l.coolerSize")
    List<Object[]> getCoolerSizeFiler();
}