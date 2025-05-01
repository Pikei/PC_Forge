package com.pc_forge.backend.model.repository.product.gpu;

import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
    List<GraphicsCard> findByChipsetProducer(String chipsetProducer);

    List<GraphicsCard> findByChipset(String chipset);

    List<GraphicsCard> findByRam(Integer ram);

    List<GraphicsCard> findByRamType(String ramType);

    List<GraphicsCard> findByDlss(String dlss);

    List<GraphicsCard> findByConnector(String connector);

    List<GraphicsCard> findByResolution(String resolution);

    List<GraphicsCard> findByRecommendedPsPower(Integer recommendedPsPower);

    List<GraphicsCard> findByCoolingType(String coolingType);

    List<GraphicsCard> findByNumberOfFans(Integer numberOfFans);

    List<GraphicsCard> findByLightning(Boolean lightning);

    List<GraphicsCard> findByCardLengthBetween(Integer cardLengthStart, Integer cardLengthEnd);


    @Query("select g.chipsetProducer, count(*) from GraphicsCard g group by g.chipsetProducer order by g.chipsetProducer")
    List<Object[]> getChipsetProducerFilter();

    @Query("select g.chipset, count(*) from GraphicsCard g group by g.chipset order by g.chipset")
    List<Object[]> getChipsetFilter();

    @Query("select g.ram, count(*) from GraphicsCard g group by g.ram order by g.ram")
    List<Object[]> getRamFilter();

    @Query("select g.ramType, count(*) from GraphicsCard g group by g.ramType order by g.ramType")
    List<Object[]> getRamTypeFilter();

    @Query("select g.dlss, count(*) from GraphicsCard g group by g.dlss order by g.dlss")
    List<Object[]> getDlssFilter();

    @Query("select g.connector, count(*) from GraphicsCard g group by g.connector order by g.connector")
    List<Object[]> getConnectorFilter();

    @Query("select g.resolution, count(*) from GraphicsCard g group by g.resolution order by g.resolution")
    List<Object[]> getResolutionFilter();

    @Query("select g.recommendedPsPower, count(*) from GraphicsCard g group by g.recommendedPsPower order by g.recommendedPsPower")
    List<Object[]> getPsPowerFilter();

    @Query("select g.coolingType, count(*) from GraphicsCard g group by g.coolingType order by g.coolingType")
    List<Object[]> getCoolingTypeFilter();

    @Query("select g.numberOfFans, count(*) from GraphicsCard g group by g.numberOfFans order by g.numberOfFans")
    List<Object[]> getNumOfFansFilter();

    @Query("select g.lightning, count(*) from GraphicsCard g group by g.lightning order by g.lightning")
    List<Object[]> getLightningFilter();

    @Query("select g.cardLength from GraphicsCard g order by g.cardLength asc limit 1")
    Integer getMinLengthFilter();

    @Query("select g.cardLength from GraphicsCard g order by g.cardLength desc limit 1")
    Integer getMaxLengthFilter();
}
