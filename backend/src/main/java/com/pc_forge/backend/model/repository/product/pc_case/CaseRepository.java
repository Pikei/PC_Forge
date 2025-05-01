package com.pc_forge.backend.model.repository.product.pc_case;

import com.pc_forge.backend.model.entity.product.pc_case.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByColor(String color);

    List<Case> findByCaseType(String caseType);

    @Query("select c from Case c join CaseMbCompatibility comp on c.id = comp.ean.id join MotherboardStandard mb on mb.id = comp.standard.id where mb.standardName like :standard")
    List<Case> findByCompatibleMbStandard(@Param("standard") String mbStandard);

    List<Case> findByWidthBetween(Double widthStart, Double widthEnd);

    List<Case> findByHeightBetween(Double heightStart, Double heightEnd);

    List<Case> findByDepthBetween(Double depthStart, Double depthEnd);

    List<Case> findByHasWindow(Boolean hasWindow);

    List<Case> findByPowerSupply(Boolean powerSupply);

    List<Case> findByPsPower(Integer psPower);

    List<Case> findByMaxGpuLengthLessThanEqual(Integer maxGpuLength);

    List<Case> findByMaxCpuCoolerHeightLessThanEqual(Integer maxCpuCoolerHeight);

    List<Case> findByFrontFans(String frontFans);

    List<Case> findByBackFans(String backFans);

    List<Case> findBySideFans(String sideFans);

    List<Case> findByBottomFans(String bottomFans);

    List<Case> findByTopFans(String topFans);

    List<Case> findByUsb20(Integer usb20);

    List<Case> findByUsb30(Integer usb30);

    List<Case> findByUsb31(Integer usb31);

    List<Case> findByUsb32(Integer usb32);

    List<Case> findByUsbC(Integer usbC);

    @Query("select c.color, count(*) from Case c group by c.color order by c.color")
    List<Object[]> getColorFilter();

    @Query("select c.caseType, count(*) from Case c group by c.caseType order by c.caseType")
    List<Object[]> getCaseTypeFilter();

    @Query("select mb.standardName, count(*) from Case c join CaseMbCompatibility comp on c.id = comp.ean.id join MotherboardStandard mb on comp.standard.id = mb.id group by mb.standardName order by mb.standardName")
    List<Object[]> getMbStandardFilter();


    @Query("select c.hasWindow, count(*) from Case c group by c.hasWindow order by c.hasWindow")
    List<Object[]> getWindowFilter();

    @Query("select c.powerSupply, count(*) from Case c group by c.powerSupply order by c.powerSupply")
    List<Object[]> getPsIncludedFilter();

    @Query("select c.psPower, count(*) from Case c group by c.psPower order by c.psPower")
    List<Object[]> getPsPowerFilter();

    @Query("select c.maxGpuLength, count(*) from Case c group by c.maxGpuLength order by c.maxGpuLength")
    List<Object[]> getMaxGpuLengthFilter();

    @Query("select c.maxCpuCoolerHeight, count(*) from Case c group by c.maxCpuCoolerHeight order by c.maxCpuCoolerHeight")
    List<Object[]> getMaxCpuHeightFilter();

    @Query("select c.frontFans, count(*) from Case c group by c.frontFans order by c.frontFans")
    List<Object[]> getFrontFansFilter();

    @Query("select c.backFans, count(*) from Case c group by c.backFans order by c.backFans")
    List<Object[]> getBackFansFilter();

    @Query("select c.sideFans, count(*) from Case c group by c.sideFans order by c.sideFans")
    List<Object[]> getSideFansFilter();

    @Query("select c.bottomFans, count(*) from Case c group by c.bottomFans order by c.bottomFans")
    List<Object[]> getBottomFansFilter();

    @Query("select c.topFans, count(*) from Case c group by c.topFans order by c.topFans")
    List<Object[]> getTopFansFilter();

    @Query("select c.usb20, count(*) from Case c group by c.usb20 order by c.usb20")
    List<Object[]> getUsb20Filter();

    @Query("select c.usb30, count(*) from Case c group by c.usb30 order by c.usb30")
    List<Object[]> getUsb30Filter();

    @Query("select c.usb31, count(*) from Case c group by c.usb31 order by c.usb31")
    List<Object[]> getUsb31Filter();

    @Query("select c.usb32, count(*) from Case c group by c.usb32 order by c.usb32")
    List<Object[]> getUsb32Filter();

    @Query("select c.usbC, count(*) from Case c group by c.usbC order by c.usbC")
    List<Object[]> getUsbCFilter();

    @Query("select c.width from Case c order by c.width asc limit 1")
    Double getMinWidth();

    @Query("select c.width from Case c order by c.width desc limit 1")
    Double getMaxWidth();

    @Query("select c.height from Case c order by c.height asc limit 1")
    Double getMinHeight();

    @Query("select c.height from Case c order by c.height desc limit 1")
    Double getMaxHeight();

    @Query("select c.depth from Case c order by c.depth asc limit 1")
    Double getMinDepth();

    @Query("select c.depth from Case c order by c.depth desc limit 1")
    Double getMaxDepth();
}