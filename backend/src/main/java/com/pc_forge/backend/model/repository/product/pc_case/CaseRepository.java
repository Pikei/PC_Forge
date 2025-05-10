package com.pc_forge.backend.model.repository.product.pc_case;

import com.pc_forge.backend.model.entity.product.pc_case.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repozytorium/DAO obudowy komputera do operacji bazodanowych.
 */

public interface CaseRepository extends JpaRepository<Case, Long> {
    /**
     * Kwerenda znajdująca wszystkie obudowy o podanym kolorze.
     *
     * @param color kolor obudowy
     * @return lista obudów w podanym kolorze
     */
    List<Case> findByColor(String color);

    /**
     * Kwerenda znajdująca wszystkie obudowy o podanym typie.
     *
     * @param caseType typ obudowy (np. ATX, Micro-ATX)
     * @return lista obudów danego typu
     */
    List<Case> findByCaseType(String caseType);

    /**
     * Kwerenda znajdująca wszystkie obudowy kompatybilne z podanym standardem płyty głównej.
     *
     * @param mbStandard nazwa standardu płyty głównej
     * @return lista obudów kompatybilnych z danym standardem
     */
    @Query("select c from Case c join CaseMbCompatibility comp on c.id = comp.ean.id join MotherboardStandard mb on mb.id = comp.standard.id where mb.standardName like :standard")
    List<Case> findByCompatibleMbStandard(@Param("standard") String mbStandard);

    /**
     * Kwerenda znajdująca wszystkie obudowy o szerokości w podanym zakresie.
     *
     * @param widthStart minimalna szerokość obudowy
     * @param widthEnd   maksymalna szerokość obudowy
     * @return lista obudów o szerokości w podanym zakresie
     */
    List<Case> findByWidthBetween(Double widthStart, Double widthEnd);

    /**
     * Kwerenda znajdująca wszystkie obudowy o wysokości w podanym zakresie.
     *
     * @param heightStart minimalna wysokość obudowy
     * @param heightEnd   maksymalna wysokość obudowy
     * @return lista obudów o wysokości w podanym zakresie
     */
    List<Case> findByHeightBetween(Double heightStart, Double heightEnd);

    /**
     * Kwerenda znajdująca wszystkie obudowy o głębokości w podanym zakresie.
     *
     * @param depthStart minimalna głębokość obudowy
     * @param depthEnd   maksymalna głębokość obudowy
     * @return lista obudów o głębokości w podanym zakresie
     */
    List<Case> findByDepthBetween(Double depthStart, Double depthEnd);

    /**
     * Kwerenda znajdująca wszystkie obudowy wyposażone lub pozbawione okna.
     *
     * @param hasWindow flaga określająca obecność okna w obudowie
     * @return lista obudów z obecnością okna
     */
    List<Case> findByHasWindow(Boolean hasWindow);

    /**
     * Kwerenda znajdująca wszystkie obudowy z załączonym zasilaczem.
     *
     * @param powerSupply flaga określająca obecność zasilacza w obudowie
     * @return lista obudów z załączonym zasilaczem
     */
    List<Case> findByPowerSupply(Boolean powerSupply);

    /**
     * Kwerenda znajdująca wszystkie obudowy o podanej mocy wbudowanego zasilacza.
     *
     * @param psPower moc wbudowanego zasilacza
     * @return lista obudów z daną mocą zasilacza
     */
    List<Case> findByPsPower(Integer psPower);

    /**
     * Kwerenda znajdująca wszystkie obudowy, które obsługują karty graficzne o zadanej maksymalnej długości.
     *
     * @param maxGpuLength maksymalna długość karty graficznej
     * @return lista obudów obsługujących karty graficzne o takiej długości
     */
    List<Case> findByMaxGpuLengthLessThanEqual(Integer maxGpuLength);

    /**
     * Kwerenda znajdująca wszystkie obudowy, które obsługują układy chłodzenia procesora
     * o zadanej maksymalnej wysokości.
     *
     * @param maxCpuCoolerHeight maksymalna wysokość układu chłodzenia procesora
     * @return lista obudów obsługujących chłodzenie o takiej wysokości
     */
    List<Case> findByMaxCpuCoolerHeightLessThanEqual(Integer maxCpuCoolerHeight);

    /**
     * Kwerenda znajdująca wszystkie obudowy z określonymi wentylatorami na panelu przednim.
     *
     * @param frontFans wentylatory na panelu przednim
     * @return lista obudów z danym frontowym chłodzeniem
     */
    List<Case> findByFrontFans(String frontFans);

    /**
     * Kwerenda znajdująca wszystkie obudowy z określonymi wentylatorami na panelu tylnym.
     *
     * @param backFans wentylatory na panelu tylnym
     * @return lista obudów z danym tylnym chłodzeniem
     */
    List<Case> findByBackFans(String backFans);

    /**
     * Kwerenda znajdująca wszystkie obudowy z określonymi wentylatorami na panelach bocznych.
     *
     * @param sideFans wentylatory na panelach bocznych
     * @return lista obudów z danym bocznym chłodzeniem
     */
    List<Case> findBySideFans(String sideFans);

    /**
     * Kwerenda znajdująca wszystkie obudowy z określonymi wentylatorami na panelu dolnym.
     *
     * @param bottomFans wentylatory na panelu dolnym
     * @return lista obudów z danym dolnym chłodzeniem
     */
    List<Case> findByBottomFans(String bottomFans);

    /**
     * Kwerenda znajdująca wszystkie obudowy z określonymi wentylatorami na panelu górnym.
     *
     * @param topFans wentylatory na panelu górnym
     * @return lista obudów z danym górnym chłodzeniem
     */
    List<Case> findByTopFans(String topFans);

    /**
     * Kwerenda znajdująca wszystkie obudowy z podaną liczbą portów USB 2.0.
     *
     * @param usb20 liczba portów USB 2.0
     * @return lista obudów spełniających podane kryterium
     */
    List<Case> findByUsb20(Integer usb20);

    /**
     * Kwerenda znajdująca wszystkie obudowy z podaną liczbą portów USB 3.0.
     *
     * @param usb30 liczba portów USB 3.0
     * @return lista obudów spełniających podane kryterium
     */
    List<Case> findByUsb30(Integer usb30);

    /**
     * Kwerenda znajdująca wszystkie obudowy z podaną liczbą portów USB 3.1.
     *
     * @param usb31 liczba portów USB 3.1
     * @return lista obudów spełniających podane kryterium
     */
    List<Case> findByUsb31(Integer usb31);

    /**
     * Kwerenda znajdująca wszystkie obudowy z podaną liczbą portów USB 3.2.
     *
     * @param usb32 liczba portów USB 3.2
     * @return lista obudów spełniających podane kryterium
     */
    List<Case> findByUsb32(Integer usb32);

    /**
     * Kwerenda znajdująca wszystkie obudowy z podaną liczbą portów USB-C.
     *
     * @param usbC liczba portów USB-C
     * @return lista obudów z daną liczbą portów USB-C
     */
    List<Case> findByUsbC(Integer usbC);

    /**
     * Zwraca dostępny filtr kolorów obudowy.
     *
     * @return lista par [kolor_obudowy, liczba_obudów]
     */
    @Query("select c.color, count(*) from Case c group by c.color order by c.color")
    List<Object[]> getColorFilter();

    /**
     * Zwraca dostępny filtr typów obudowy.
     *
     * @return lista par [typ_obudowy, liczba_obudów]
     */
    @Query("select c.caseType, count(*) from Case c group by c.caseType order by c.caseType")
    List<Object[]> getCaseTypeFilter();

    /**
     * Zwraca dostępny filtr standardów płyt głównych.
     *
     * @return lista par [nazwa_standardu, liczba_obudów]
     */
    @Query("select mb.standardName, count(*) from Case c join CaseMbCompatibility comp on c.id = comp.ean.id join MotherboardStandard mb on comp.standard.id = mb.id group by mb.standardName order by mb.standardName")
    List<Object[]> getMbStandardFilter();

    /**
     * Zwraca dostępny filtr okien w obudowie.
     *
     * @return lista par [ma_okno, liczba_obudów]
     */
    @Query("select c.hasWindow, count(*) from Case c group by c.hasWindow order by c.hasWindow")
    List<Object[]> getWindowFilter();

    /**
     * Zwraca dostępny filtr obecności zasilacza.
     *
     * @return lista par [ma_zasilacz, liczba_obudów]
     */
    @Query("select c.powerSupply, count(*) from Case c group by c.powerSupply order by c.powerSupply")
    List<Object[]> getPsIncludedFilter();

    /**
     * Zwraca dostępny filtr mocy zasilaczy.
     *
     * @return lista par [moc_zasilacza, liczba_obudów]
     */
    @Query("select c.psPower, count(*) from Case c group by c.psPower order by c.psPower")
    List<Object[]> getPsPowerFilter();

    /**
     * Zwraca dostępny filtr maksymalnych długości kart graficznych.
     *
     * @return lista par [maks_długość_karty, liczba_obudów]
     */
    @Query("select c.maxGpuLength, count(*) from Case c group by c.maxGpuLength order by c.maxGpuLength")
    List<Object[]> getMaxGpuLengthFilter();

    /**
     * Zwraca dostępny filtr maksymalnych wysokości chłodzeń CPU.
     *
     * @return lista par [maks_wysokość_chłodzenia, liczba_obudów]
     */
    @Query("select c.maxCpuCoolerHeight, count(*) from Case c group by c.maxCpuCoolerHeight order by c.maxCpuCoolerHeight")
    List<Object[]> getMaxCpuHeightFilter();

    /**
     * Zwraca dostępny filtr wentylatorów przednich.
     *
     * @return lista par [wentylatory_przednie, liczba_obudów]
     */
    @Query("select c.frontFans, count(*) from Case c group by c.frontFans order by c.frontFans")
    List<Object[]> getFrontFansFilter();

    /**
     * Zwraca dostępny filtr wentylatorów tylnych.
     *
     * @return lista par [wentylatory_tylne, liczba_obudów]
     */
    @Query("select c.backFans, count(*) from Case c group by c.backFans order by c.backFans")
    List<Object[]> getBackFansFilter();

    /**
     * Zwraca dostępny filtr wentylatorów bocznych.
     *
     * @return lista par [wentylatory_boczne, liczba_obudów]
     */
    @Query("select c.sideFans, count(*) from Case c group by c.sideFans order by c.sideFans")
    List<Object[]> getSideFansFilter();

    /**
     * Zwraca dostępny filtr wentylatorów dolnych.
     *
     * @return lista par [wentylatory_dolne, liczba_obudów]
     */
    @Query("select c.bottomFans, count(*) from Case c group by c.bottomFans order by c.bottomFans")
    List<Object[]> getBottomFansFilter();

    /**
     * Zwraca dostępny filtr wentylatorów górnych.
     *
     * @return lista par [wentylatory_górne, liczba_obudów]
     */
    @Query("select c.topFans, count(*) from Case c group by c.topFans order by c.topFans")
    List<Object[]> getTopFansFilter();

    /**
     * Zwraca dostępny filtr portów USB 2.0.
     *
     * @return lista par [liczba_portów_usb_2.0, liczba_obudów]
     */
    @Query("select c.usb20, count(*) from Case c group by c.usb20 order by c.usb20")
    List<Object[]> getUsb20Filter();

    /**
     * Zwraca dostępny filtr portów USB 3.0.
     *
     * @return lista par [liczba_portów_usb_3.0, liczba_obudów]
     */
    @Query("select c.usb30, count(*) from Case c group by c.usb30 order by c.usb30")
    List<Object[]> getUsb30Filter();

    /**
     * Zwraca dostępny filtr portów USB 3.1.
     *
     * @return lista par [liczba_portów_usb_3.1, liczba_obudów]
     */
    @Query("select c.usb31, count(*) from Case c group by c.usb31 order by c.usb31")
    List<Object[]> getUsb31Filter();

    /**
     * Zwraca dostępny filtr portów USB 3.2.
     *
     * @return lista par [liczba_portów_usb_3.2, liczba_obudów]
     */
    @Query("select c.usb32, count(*) from Case c group by c.usb32 order by c.usb32")
    List<Object[]> getUsb32Filter();

    /**
     * Zwraca dostępny filtr portów USB-C.
     *
     * @return lista par [liczba_portów_usb_c, liczba_obudów]
     */
    @Query("select c.usbC, count(*) from Case c group by c.usbC order by c.usbC")
    List<Object[]> getUsbCFilter();

    /**
     * Zwraca minimalną szerokość dostępnych obudów.
     *
     * @return minimalna szerokość obudowy
     */
    @Query("select c.width from Case c order by c.width asc limit 1")
    Double getMinWidth();

    /**
     * Zwraca maksymalną szerokość dostępnych obudów.
     *
     * @return maksymalna szerokość obudowy
     */
    @Query("select c.width from Case c order by c.width desc limit 1")
    Double getMaxWidth();

    /**
     * Zwraca minimalną wysokość dostępnych obudów.
     *
     * @return minimalna wysokość obudowy
     */
    @Query("select c.height from Case c order by c.height asc limit 1")
    Double getMinHeight();

    /**
     * Zwraca maksymalną wysokość dostępnych obudów.
     *
     * @return maksymalna wysokość obudowy
     */
    @Query("select c.height from Case c order by c.height desc limit 1")
    Double getMaxHeight();

    /**
     * Zwraca minimalną głębokość dostępnych obudów.
     *
     * @return minimalna głębokość obudowy
     */
    @Query("select c.depth from Case c order by c.depth asc limit 1")
    Double getMinDepth();

    /**
     * Zwraca maksymalną głębokość dostępnych obudów.
     *
     * @return maksymalna głębokość obudowy
     */
    @Query("select c.depth from Case c order by c.depth desc limit 1")
    Double getMaxDepth();
}