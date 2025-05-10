package com.pc_forge.backend.model.repository.product.cpu;

import com.pc_forge.backend.model.entity.product.cpu.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Repozytorium/DAO procesora do operacji bazodanowych.
 */
public interface ProcessorRepository extends JpaRepository<Processor, Long> {
    /**
     * Kwerenda znajdująca wszystkie procesory o podanej nazwie gniazda.
     *
     * @param socketName nazwa gniazda procesora
     * @return lista procesorów z danym gniazdem
     */
    List<Processor> findBySocket_SocketName(String socketName);

    /**
     * Kwerenda znajdująca wszystkie procesory z danej linii.
     *
     * @param line linia procesora
     * @return lista procesorów z danej linii
     */
    List<Processor> findByLine(String line);

    /**
     * Kwerenda znajdująca wszystkie procesory o podanej liczbie rdzeni.
     *
     * @param cores liczba rdzeni
     * @return lista procesorów z daną liczbą rdzeni
     */
    List<Processor> findByCores(Integer cores);

    /**
     * Kwerenda znajdująca wszystkie procesory według stanu odblokowania mnożnika.
     *
     * @param unlocked flaga określająca, czy procesor ma odblokowany mnożnik
     * @return lista procesorów z danym stanem odblokowania
     */
    List<Processor> findByUnlocked(Boolean unlocked);

    /**
     * Kwerenda znajdująca wszystkie procesory o podanej częstotliwości bazowej.
     *
     * @param frequency częstotliwość taktowania procesora
     * @return lista procesorów o danej częstotliwości
     */
    List<Processor> findByFrequency(Double frequency);

    /**
     * Kwerenda znajdująca procesory po zintegrowanym układzie graficznym.
     *
     * @param integratedGraphicsUnit nazwa układu graficznego
     * @return lista procesorów z danym układem graficznym
     */
    @Query("select p from Processor p where (:unit is null and p.integratedGraphicsUnit is null) or (:unit is not null and p.integratedGraphicsUnit = :unit)")
    List<Processor> findByIntegratedGraphicsUnit(@Nullable @Param("unit") String integratedGraphicsUnit);

    /**
     * Kwerenda znajdująca procesory według obecności dołączonego chłodzenia.
     *
     * @param coolerIncluded flaga określająca czy procesor ma załączony układ chłodzenia
     * @return lista procesorów z danym stanem chłodzenia
     */
    List<Processor> findByCoolerIncluded(Boolean coolerIncluded);

    /**
     * Kwerenda znajdująca procesory o danym typie opakowania.
     *
     * @param packaging typ opakowania (np. BOX, OEM)
     * @return lista procesorów z danym opakowaniem
     */
    List<Processor> findByPackaging(String packaging);

    /**
     * Zwraca dostępny filtr gniazda procesora, zawierający listę nazw gniazd procesora i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [nazwa_gniazda, liczba_procesorów]
     */
    @Query("select p.socket.socketName, count(*) from Processor p group by p.socket.socketName order by p.socket.socketName")
    List<Object[]> getSocketFilter();

    /**
     * Zwraca dostępny filtr linii procesora, zawierający listę nazw linii procesora i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [nazwa_linii, liczba_procesorów]
     */
    @Query("select concat(p.producer, ' ', p.line), count(*) from Processor p group by concat(p.producer, ' ', p.line) order by concat(p.producer, ' ', p.line)")
    List<Object[]> getLineFilter();

    /**
     * Zwraca dostępny filtr liczby rdzeni procesora, zawierający listę liczb rdzeni procesora i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [liczba_rdzeni_procesora, liczba_procesorów]
     */
    @Query("select p.cores, count(*) from Processor p group by p.cores order by p.cores")
    List<Object[]> getCoresFilter();

    /**
     * Zwraca dostępny filtr odblokowania mnożnika procesora, zawierający listę stanów odblokowania i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [status_odblokowania, liczba_procesorów]
     */
    @Query("select p.unlocked, count(*) from Processor p group by p.unlocked order by p.unlocked")
    List<Object[]> getUnlockedFilter();

    /**
     * Zwraca dostępny filtr częstotliwości bazowej procesora, zawierający listę częstotliwości i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [częstotliwość_bazowa, liczba_procesorów]
     */
    @Query("select p.frequency, count(*) from Processor p group by p.frequency order by p.frequency")
    List<Object[]> getFrequencyFilter();

    /**
     * Zwraca dostępny filtr zintegrowanych układów graficznych, zawierający listę układów graficznych i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [układ_graficzny, liczba_procesorów]
     */
    @Query("select p.integratedGraphicsUnit, count(*) from Processor p group by p.integratedGraphicsUnit order by p.integratedGraphicsUnit nulls first")
    List<Object[]> getIntegratedGraphicsUnitFilter();

    /**
     * Zwraca dostępny filtr dołączonego chłodzenia, zawierający listę stanów dołączenia chłodzenia i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [status_chłodzenia, liczba_procesorów]
     */
    @Query("select p.coolerIncluded, count(*) from Processor p group by p.coolerIncluded order by p.coolerIncluded")
    List<Object[]> getCoolerIncludedFilter();

    /**
     * Zwraca dostępny filtr typu opakowania, zawierający listę typów opakowań i liczby produktów
     * spełniających dane wymagania.
     *
     * @return lista par [typ_opakowania, liczba_procesorów]
     */
    @Query("select p.packaging, count(*) from Processor p group by p.packaging order by p.packaging")
    List<Object[]> getPackagingFilter();
}
