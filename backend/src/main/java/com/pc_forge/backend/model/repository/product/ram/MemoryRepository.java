package com.pc_forge.backend.model.repository.product.ram;

import com.pc_forge.backend.model.entity.product.ram.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO modułów pamięci RAM, umożliwiające operacje bazodanowe.
 */
public interface MemoryRepository extends JpaRepository<Memory, Long> {

    /**
     * Kwerenda znajdująca moduły pamięci RAM według typu pamięci.
     *
     * @param memoryType typ pamięci RAM
     * @return lista modułów pamięci RAM o podanym typie
     */
    List<Memory> findByMemoryType(String memoryType);

    /**
     * Kwerenda znajdująca moduły pamięci RAM według całkowitej pojemności.
     *
     * @param totalCapacity całkowita pojemność (w GB)
     * @return lista modułów pamięci RAM o podanej pojemności
     */
    List<Memory> findByTotalCapacity(Integer totalCapacity);

    /**
     * Kwerenda znajdująca moduły pamięci RAM według częstotliwości taktowania.
     *
     * @param frequency częstotliwość taktowania (w MHz)
     * @return lista modułów pamięci RAM o podanej częstotliwości taktowania
     */
    List<Memory> findByFrequency(Integer frequency);

    /**
     * Kwerenda znajdująca moduły pamięci RAM według liczby modułów w zestawie.
     *
     * @param numberOfModules liczba modułów w zestawie
     * @return lista modułów pamięci RAM o podanej liczbie modułów
     */
    List<Memory> findByNumberOfModules(Integer numberOfModules);

    /**
     * Kwerenda znajdująca moduły pamięci RAM według opóźnienia zegara pamięci.
     *
     * @param latency opóźnienie zegara pamięci (np. CL16)
     * @return lista modułów pamięci RAM o podanym opóźnieniu zegara
     */
    List<Memory> findByLatency(String latency);

    /**
     * Kwerenda znajdująca moduły pamięci RAM z podświetleniem lub bez.
     *
     * @param lighting flaga określająca obecność podświetlenia
     * @return lista modułów pamięci RAM o podanym stanie podświetlenia
     */
    List<Memory> findByLighting(Boolean lighting);

    /**
     * Zwraca dostępny filtr typów pamięci RAM.
     *
     * @return lista par [typ_pamięci, liczba_modułów]
     */
    @Query("select m.memoryType, count(*) from Memory m group by m.memoryType order by m.memoryType desc")
    List<Object[]> getMemoryTypeFilter();

    /**
     * Zwraca dostępny filtr pojemności pamięci RAM.
     *
     * @return lista par [pojemność, liczba_modułów]
     */
    @Query("select m.totalCapacity, count(*) from Memory m group by m.totalCapacity order by m.totalCapacity desc")
    List<Object[]> getTotalCapacityFilter();

    /**
     * Zwraca dostępny filtr częstotliwości taktowania pamięci RAM.
     *
     * @return lista par [częstotliwość, liczba_modułów]
     */
    @Query("select m.frequency, count(*) from Memory m group by m.frequency order by m.frequency desc")
    List<Object[]> getFrequencyFilter();

    /**
     * Zwraca dostępny filtr liczby modułów pamięci RAM.
     *
     * @return lista par [liczba_modułów, liczba_zestawów]
     */
    @Query("select m.numberOfModules, count(*) from Memory m group by m.numberOfModules order by m.numberOfModules desc")
    List<Object[]> getNumberOfModulesFilter();

    /**
     * Zwraca dostępny filtr opóźnień zegara pamięci RAM.
     *
     * @return lista par [opóźnienie, liczba_modułów]
     */
    @Query("select m.latency, count(*) from Memory m group by m.latency order by m.latency desc")
    List<Object[]> getLatencyFilter();

    /**
     * Zwraca dostępny filtr podświetlenia modułów pamięci RAM.
     *
     * @return lista par [podświetlenie, liczba_modułów]
     */
    @Query("select m.lighting, count(*) from Memory m group by m.lighting order by m.lighting desc")
    List<Object[]> getLightningFilter();
}