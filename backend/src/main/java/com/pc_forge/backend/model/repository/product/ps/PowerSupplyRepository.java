package com.pc_forge.backend.model.repository.product.ps;

import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {
    List<PowerSupply> findByPower(Integer power);

    List<PowerSupply> findByEfficiencyCertificate(String efficiencyCertificate);

    List<PowerSupply> findByEfficiency(Integer efficiency);

    @Query(value = "select * from power_supply as ps join product as prod on ps.ean = prod.ean where protections @> array[?1]::varchar[]", nativeQuery = true)
    List<PowerSupply> findByProtection(String protection);

    List<PowerSupply> findByCoolingType(String coolingType);

    List<PowerSupply> findByFanDiameter(Integer fanDiameter);

    List<PowerSupply> findByModularCabling(Boolean modularCabling);

    List<PowerSupply> findByLightning(Boolean lightning);

    @Query("select ps.power, count(*) from PowerSupply ps group by ps.power order by ps.power")
    List<Object[]> getPowerFilter();

    @Query("select ps.efficiencyCertificate, count(*) from PowerSupply ps group by ps.efficiencyCertificate order by ps.efficiencyCertificate")
    List<Object[]> getEfficiencyCertificateFilter();

    @Query(value = "select distinct unnest(protections) as protection, count(*) from power_supply group by protection order by protection", nativeQuery = true)
    List<Object[]> getProtectionFilter();

    @Query("select ps.efficiency, count(*) from PowerSupply ps group by ps.efficiency order by ps.efficiency")
    List<Object[]> getEfficiencyFilter();

    @Query("select ps.coolingType, count(*) from PowerSupply ps group by ps.coolingType order by ps.coolingType")
    List<Object[]> getCoolingTypeFilter();

    @Query("select ps.fanDiameter, count(*) from PowerSupply ps group by ps.fanDiameter order by ps.fanDiameter")
    List<Object[]> getFanDiameterFilter();

    @Query("select ps.modularCabling, count(*) from PowerSupply ps group by ps.modularCabling order by ps.modularCabling")
    List<Object[]> getModularCablingFilter();

    @Query("select ps.lightning, count(*) from PowerSupply ps group by ps.lightning order by ps.lightning")
    List<Object[]> getLightningFilter();
}