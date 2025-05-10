package com.pc_forge.backend.model.repository.product.gpu;

import com.pc_forge.backend.model.entity.product.gpu.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO karty graficznej do operacji bazodanowych.
 */
public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {

    /**
     * Kwerenda znajdująca wszystkie karty graficzne o podanym producencie chipsetu.
     *
     * @param chipsetProducer producent chipsetu karty graficznej
     * @return lista kart graficznych z danym producentem chipsetu
     */
    List<GraphicsCard> findByChipsetProducer(String chipsetProducer);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne z danym chipsetem.
     *
     * @param chipset chipset karty graficznej
     * @return lista kart graficznych z danym chipsetem
     */
    List<GraphicsCard> findByChipset(String chipset);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne o podanej ilości pamięci RAM.
     *
     * @param ram ilość pamięci RAM
     * @return lista kart graficznych z danym rozmiarem pamięci RAM
     */
    List<GraphicsCard> findByRam(Integer ram);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne z danym typem pamięci RAM.
     *
     * @param ramType typ pamięci RAM
     * @return lista kart graficznych z danym typem pamięci RAM
     */
    List<GraphicsCard> findByRamType(String ramType);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne wspierające podaną technologię DLSS.
     *
     * @param dlss technologia DLSS
     * @return lista kart graficznych wspierających podaną technologię DLSS
     */
    List<GraphicsCard> findByDlss(String dlss);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne z podanym złączem.
     *
     * @param connector typ złącza
     * @return lista kart graficznych z danym złączem
     */
    List<GraphicsCard> findByConnector(String connector);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne obsługujące podaną rozdzielczość obrazu.
     *
     * @param resolution rozdzielczość obrazu
     * @return lista kart graficznych obsługujących podaną rozdzielczość
     */
    List<GraphicsCard> findByResolution(String resolution);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne wymagające podanej mocy zasilacza.
     *
     * @param recommendedPsPower rekomendowana moc zasilacza
     * @return lista kart graficznych wymagających danej mocy zasilacza
     */
    List<GraphicsCard> findByRecommendedPsPower(Integer recommendedPsPower);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne z podanym typem chłodzenia.
     *
     * @param coolingType typ chłodzenia
     * @return lista kart graficznych z danym typem chłodzenia
     */
    List<GraphicsCard> findByCoolingType(String coolingType);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne z podaną liczbą wiatraków.
     *
     * @param numberOfFans liczba wiatraków
     * @return lista kart graficznych z daną liczbą wiatraków
     */
    List<GraphicsCard> findByNumberOfFans(Integer numberOfFans);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne wyposażone w podświetlenie lub bez.
     *
     * @param lightning flaga określająca czy karta graficzna posiada podświetlenie
     * @return lista kart graficznych z danym stanem podświetlenia
     */
    List<GraphicsCard> findByLightning(Boolean lightning);

    /**
     * Kwerenda znajdująca wszystkie karty graficzne o długości w podanym przedziale.
     *
     * @param cardLengthStart początkowa długość karty w mm
     * @param cardLengthEnd   końcowa długość karty w mm
     * @return lista kart graficznych w podanym zakresie długości
     */
    List<GraphicsCard> findByCardLengthBetween(Integer cardLengthStart, Integer cardLengthEnd);

    /**
     * Zwraca dostępny filtr producentów chipsetów karty graficznej.
     *
     * @return lista par [producent_chipsetu, liczba_kart]
     */
    @Query("select g.chipsetProducer, count(*) from GraphicsCard g group by g.chipsetProducer order by g.chipsetProducer")
    List<Object[]> getChipsetProducerFilter();

    /**
     * Zwraca dostępny filtr chipsetów karty graficznej.
     *
     * @return lista par [chipset, liczba_kart]
     */
    @Query("select g.chipset, count(*) from GraphicsCard g group by g.chipset order by g.chipset")
    List<Object[]> getChipsetFilter();

    /**
     * Zwraca dostępny filtr ilości pamięci RAM karty graficznej.
     *
     * @return lista par [ilość_pamięci_RAM, liczba_kart]
     */
    @Query("select g.ram, count(*) from GraphicsCard g group by g.ram order by g.ram")
    List<Object[]> getRamFilter();

    /**
     * Zwraca dostępny filtr typów pamięci RAM.
     *
     * @return lista par [typ_pamięci_RAM, liczba_kart]
     */
    @Query("select g.ramType, count(*) from GraphicsCard g group by g.ramType order by g.ramType")
    List<Object[]> getRamTypeFilter();

    /**
     * Zwraca dostępny filtr technologii DLSS.
     *
     * @return lista par [technologia_DLSS, liczba_kart]
     */
    @Query("select g.dlss, count(*) from GraphicsCard g group by g.dlss order by g.dlss")
    List<Object[]> getDlssFilter();

    /**
     * Zwraca dostępny filtr typów złączy kart graficznych.
     *
     * @return lista par [typ_złącza, liczba_kart]
     */
    @Query("select g.connector, count(*) from GraphicsCard g group by g.connector order by g.connector")
    List<Object[]> getConnectorFilter();

    /**
     * Zwraca dostępny filtr obsługiwanych rozdzielczości.
     *
     * @return lista par [rozdzielczość, liczba_kart]
     */
    @Query("select g.resolution, count(*) from GraphicsCard g group by g.resolution order by g.resolution")
    List<Object[]> getResolutionFilter();

    /**
     * Zwraca dostępny filtr wymaganej mocy zasilacza.
     *
     * @return lista par [rekomendowana_moc_zasilacza, liczba_kart]
     */
    @Query("select g.recommendedPsPower, count(*) from GraphicsCard g group by g.recommendedPsPower order by g.recommendedPsPower")
    List<Object[]> getPsPowerFilter();

    /**
     * Zwraca dostępny filtr typów chłodzenia.
     *
     * @return lista par [typ_chłodzenia, liczba_kart]
     */
    @Query("select g.coolingType, count(*) from GraphicsCard g group by g.coolingType order by g.coolingType")
    List<Object[]> getCoolingTypeFilter();

    /**
     * Zwraca dostępny filtr liczby wiatraków.
     *
     * @return lista par [liczba_wiatraków, liczba_kart]
     */
    @Query("select g.numberOfFans, count(*) from GraphicsCard g group by g.numberOfFans order by g.numberOfFans")
    List<Object[]> getNumOfFansFilter();

    /**
     * Zwraca dostępny filtr kart graficznych z podświetleniem.
     *
     * @return lista par [stan_podświetlenia, liczba_kart]
     */
    @Query("select g.lightning, count(*) from GraphicsCard g group by g.lightning order by g.lightning")
    List<Object[]> getLightningFilter();

    /**
     * Zwraca minimalną długość dostępnej karty graficznej.
     *
     * @return minimalna długość karty graficznej w mm
     */
    @Query("select g.cardLength from GraphicsCard g order by g.cardLength asc limit 1")
    Integer getMinLengthFilter();

    /**
     * Zwraca maksymalną długość dostępnej karty graficznej.
     *
     * @return maksymalna długość karty graficznej w mm
     */
    @Query("select g.cardLength from GraphicsCard g order by g.cardLength desc limit 1")
    Integer getMaxLengthFilter();
}