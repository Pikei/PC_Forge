package com.pc_forge.backend.model.repository.product.ps;

import com.pc_forge.backend.model.entity.product.ps.PowerSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO zasilaczy komputerowych, umożliwiające operacje bazodanowe.
 */
public interface PowerSupplyRepository extends JpaRepository<PowerSupply, Long> {

    /**
     * Kwerenda znajdująca zasilacze o podanej mocy.
     *
     * @param power moc zasilacza (w watach)
     * @return lista zasilaczy o podanej mocy
     */
    List<PowerSupply> findByPower(Integer power);

    /**
     * Kwerenda znajdująca zasilacze z określonym certyfikatem sprawności.
     *
     * @param efficiencyCertificate typ certyfikatu sprawności (np. 80+ Gold, 80+ Platinum)
     * @return lista zasilaczy z podanym certyfikatem sprawności
     */
    List<PowerSupply> findByEfficiencyCertificate(String efficiencyCertificate);

    /**
     * Kwerenda znajdująca zasilacze o określonej procentowej sprawności.
     *
     * @param efficiency procentowa sprawność zasilacza
     * @return lista zasilaczy o podanej sprawności procentowej
     */
    List<PowerSupply> findByEfficiency(Integer efficiency);

    /**
     * Kwerenda znajdująca zasilacze z danym typem zabezpieczeń.
     *
     * @param protection typ zabezpieczenia (np. OVP, UVP, SCP)
     * @return lista zasilaczy z podanym typem zabezpieczenia
     */
    @Query(value = "select * from power_supply as ps join product as prod on ps.ean = prod.ean where protections @> array[?1]::varchar[]", nativeQuery = true)
    List<PowerSupply> findByProtection(String protection);

    /**
     * Kwerenda znajdująca zasilacze z chłodzeniem określonego typu.
     *
     * @param coolingType typ chłodzenia (np. aktywne, pasywne)
     * @return lista zasilaczy z podanym typem chłodzenia
     */
    List<PowerSupply> findByCoolingType(String coolingType);

    /**
     * Kwerenda znajdująca zasilacze z podaną średnicą wentylatorów.
     *
     * @param fanDiameter średnica wentylatora (w mm)
     * @return lista zasilaczy z podaną średnicą wentylatorów
     */
    List<PowerSupply> findByFanDiameter(Integer fanDiameter);

    /**
     * Kwerenda znajdująca zasilacze z modularnym okablowaniem.
     *
     * @param modularCabling flaga określająca obecność modularnego okablowania
     * @return lista zasilaczy z modularnym okablowaniem
     */
    List<PowerSupply> findByModularCabling(Boolean modularCabling);

    /**
     * Kwerenda znajdująca zasilacze z podświetleniem lub bez.
     *
     * @param lightning flaga określająca obecność podświetlenia
     * @return lista zasilaczy z podanym stanem podświetlenia
     */
    List<PowerSupply> findByLightning(Boolean lightning);

    /**
     * Zwraca dostępny filtr mocy zasilaczy.
     *
     * @return lista par [moc, liczba_zasilaczy]
     */
    @Query("select ps.power, count(*) from PowerSupply ps group by ps.power order by ps.power")
    List<Object[]> getPowerFilter();

    /**
     * Zwraca dostępny filtr certyfikatów sprawności zasilaczy.
     *
     * @return lista par [certyfikat, liczba_zasilaczy]
     */
    @Query("select ps.efficiencyCertificate, count(*) from PowerSupply ps group by ps.efficiencyCertificate order by ps.efficiencyCertificate")
    List<Object[]> getEfficiencyCertificateFilter();

    /**
     * Zwraca dostępny filtr zabezpieczeń zasilaczy.
     *
     * @return lista par [zabezpieczenie, liczba_zasilaczy]
     */
    @Query(value = "select distinct unnest(protections) as protection, count(*) from power_supply group by protection order by protection", nativeQuery = true)
    List<Object[]> getProtectionFilter();

    /**
     * Zwraca dostępny filtr sprawności zasilaczy.
     *
     * @return lista par [sprawność, liczba_zasilaczy]
     */
    @Query("select ps.efficiency, count(*) from PowerSupply ps group by ps.efficiency order by ps.efficiency")
    List<Object[]> getEfficiencyFilter();

    /**
     * Zwraca dostępny filtr typów chłodzenia zasilaczy.
     *
     * @return lista par [typ_chłodzenia, liczba_zasilaczy]
     */
    @Query("select ps.coolingType, count(*) from PowerSupply ps group by ps.coolingType order by ps.coolingType")
    List<Object[]> getCoolingTypeFilter();

    /**
     * Zwraca dostępny filtr średnic wentylatorów zasilaczy.
     *
     * @return lista par [średnica_wentylatorów, liczba_zasilaczy]
     */
    @Query("select ps.fanDiameter, count(*) from PowerSupply ps group by ps.fanDiameter order by ps.fanDiameter")
    List<Object[]> getFanDiameterFilter();

    /**
     * Zwraca dostępny filtr modularności okablowania zasilaczy.
     *
     * @return lista par [modularność, liczba_zasilaczy]
     */
    @Query("select ps.modularCabling, count(*) from PowerSupply ps group by ps.modularCabling order by ps.modularCabling")
    List<Object[]> getModularCablingFilter();

    /**
     * Zwraca dostępny filtr podświetlenia zasilaczy.
     *
     * @return lista par [podświetlenie, liczba_zasilaczy]
     */
    @Query("select ps.lightning, count(*) from PowerSupply ps group by ps.lightning order by ps.lightning")
    List<Object[]> getLightningFilter();
}
