package com.pc_forge.backend.model.repository.product.cooler;

import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirCoolerRepository extends JpaRepository<AirCooler, Long> {

    @Query("select a from AirCooler a join CoolerSocketCompatibility c on a.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId where s.socketName like :socket")
    List<AirCooler> findBySocket(@Param("socket") String socket);

    List<AirCooler> findByFans(Integer fans);

    List<AirCooler> findByFanDiameter(Integer fanDiameter);

    List<AirCooler> findByNoiseLevel(Double noiseLevel);

    List<AirCooler> findByLightning(Boolean lightning);

    List<AirCooler> findByBaseMaterial(String baseMaterial);

    List<AirCooler> findByWidthBetween(Integer widthStart, Integer widthEnd);

    List<AirCooler> findByHeightBetween(Integer heightStart, Integer heightEnd);

    List<AirCooler> findByDepthBetween(Integer depthStart, Integer depthEnd);

    List<AirCooler> findByVerticalInstallation(Boolean verticalInstallation);


    @Query("select a.width from AirCooler a order by a.width asc limit 1")
    Integer getMinWidth();

    @Query("select a.width from AirCooler a order by a.width desc limit 1")
    Integer getMaxWidth();

    @Query("select a.height from AirCooler a order by a.height asc limit 1")
    Integer getMinHeight();

    @Query("select a.height from AirCooler a order by a.height desc limit 1")
    Integer getMaxHeight();

    @Query("select a.depth from AirCooler a order by a.depth asc limit 1")
    Integer getMinDepth();

    @Query("select a.depth from AirCooler a order by a.depth desc limit 1")
    Integer getMaxDepth();

    @Query("select s.socketName, count(*) from AirCooler a join CoolerSocketCompatibility c on a.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId group by s.socketName order by s.socketName")
    List<Object[]> getSocketFiler();

    @Query("select a.fans, count(*) from AirCooler a group by a.fans order by a.fans")
    List<Object[]> getNumOfFansFiler();

    @Query("select a.fanDiameter, count(*) from AirCooler a group by a.fanDiameter order by a.fanDiameter")
    List<Object[]> getFanDiameterFiler();

    @Query("select a.noiseLevel, count(*) from AirCooler a group by a.noiseLevel order by a.noiseLevel")
    List<Object[]> getNoiseLvlFiler();

    @Query("select a.lightning, count(*) from AirCooler a group by a.lightning order by a.lightning")
    List<Object[]> getLightningFiler();

    @Query("select a.baseMaterial, count(*) from AirCooler a group by a.baseMaterial order by a.baseMaterial")
    List<Object[]> getBaseMaterialFiler();

    @Query("select a.verticalInstallation, count(*) from AirCooler a group by a.verticalInstallation order by a.verticalInstallation")
    List<Object[]> getVerticalInstallationFiler();

}