package com.pc_forge.backend.model.repository.product.mb;

import com.pc_forge.backend.model.entity.product.mb.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO płyty głównej do operacji bazodanowych.
 */
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
    /**
     * Kwerenda znajdująca płyty główne po nazwie gniazda procesora
     *
     * @param socketName nazwa gniazda
     * @return lista płyt głównych z danym gniazdem
     */
    List<Motherboard> findBySocket_SocketName(String socketName);

    /**
     * Kwerenda znajdująca płyty główne po nazwie standardu obudowy
     *
     * @param standardName nazwa standardu
     * @return lista płyt głównych o danym standardzie
     */
    List<Motherboard> findByStandard_StandardName(String standardName);

    /**
     * Kwerenda znajdująca wszystkie płyty główne o podanym chipsecie.
     *
     * @param chipset nazwa chipsetu
     * @return lista płyt głównych posiadających wskazany chipset
     */
    List<Motherboard> findByChipset(String chipset);

    /**
     * Kwerenda znajdująca płyty główne po standardzie pamięci RAM
     *
     * @param memoryStandard standard obsługiwanej pamięci
     * @return lista płyt głównych obsługujących dany standard pamięci
     */
    List<Motherboard> findByMemoryStandard(String memoryStandard);

    /**
     * Kwerenda znajdująca płyty główne po liczbie slotów pamięci RAM
     *
     * @param memorySlots ilość slotów pamięci
     * @return lista płyt głównych z daną ilością slotów
     */
    List<Motherboard> findByMemorySlots(Integer memorySlots);

    /**
     * Kwerenda znajdująca płyty główne po maksymalnej pojemności obsługiwanej pamięci RAM
     *
     * @param maxMemoryCapacity maksymalna pojemność
     * @return lista płyt głównych obsługujących daną maksymalną pojemność
     */
    List<Motherboard> findByMaxMemoryCapacity(Integer maxMemoryCapacity);

    /**
     * Kwerenda znajdująca płyty główne obsługujące daną częstotliwość pamięci RAM
     *
     * @param frequency częstotliwość
     * @return lista płyt głównych obsługujących daną częstotliwość
     */
    @Query(value = "select * from motherboard as mb join product as prod on mb.ean = prod.ean where supported_memory_frequencies @> array[?1]", nativeQuery = true)
    List<Motherboard> findBySupportedMemoryFrequency(Integer frequency);

    /**
     * Kwerenda znajdująca płyty główne po obecności modułu Bluetooth
     *
     * @param bluetooth flaga określająca, czy płyta główna posiada moduł Bluetooth
     * @return lista płyt głównych spełniająca wymagania dotyczące modułu Bluetooth
     */
    List<Motherboard> findByBluetooth(Boolean bluetooth);

    /**
     * Kwerenda znajdująca płyty główne po obecności modułu WiFi
     *
     * @param wifi flaga określająca, czy płyta główna posiada moduł WiFi
     * @return lista płyt głównych spełniająca wymagania dotyczące modułu WiFi
     */
    List<Motherboard> findByWifi(Boolean wifi);

    /**
     * Kwerenda znajdująca płyty główne po zakresie szerokości
     *
     * @param widthStart minimalna szerokość
     * @param widthEnd   maksymalna szerokość
     * @return lista płyt głównych w podanym zakresie szerokości
     */
    List<Motherboard> findByWidthBetween(Double widthStart, Double widthEnd);

    /**
     * Kwerenda znajdująca płyty główne po zakresie głębokości
     *
     * @param depthStart minimalna głębokość
     * @param depthEnd   maksymalna głębokość
     * @return lista płyt głównych w podanym zakresie głębokości
     */
    List<Motherboard> findByDepthBetween(Double depthStart, Double depthEnd);

    /**
     * Zwraca dostępny filtr gniazd procesora.
     *
     * @return lista par [nazwa_gniazda_procesora, liczba_kart]
     */
    @Query("select m.socket.socketName, count(*) from Motherboard m group by m.socket.socketName order by m.socket.socketName")
    List<Object[]> getSocketFilter();

    /**
     * Zwraca dostępne opcje filtrowania po standardach płyty głównej.
     *
     * @return lista par [nazwa_standardu, liczba_kart]
     */
    @Query("select m.standard.standardName, count(*) from Motherboard m group by m.standard.standardName order by m.standard.standardName")
    List<Object[]> getStandardFilter();

    /**
     * Zwraca dostępne opcje filtrowania po chipsetach.
     *
     * @return lista par [chipset, liczba_płyt]
     */
    @Query("select m.chipset, count(*) from Motherboard m group by m.chipset order by m.chipset")
    List<Object[]> getChipsetFilter();

    /**
     * Zwraca dostępne opcje filtrowania po standardach pamięci RAM.
     *
     * @return lista par [standard_pamięci, liczba_płyt]
     */
    @Query("select m.memoryStandard, count(*) from Motherboard m group by m.memoryStandard order by m.memoryStandard")
    List<Object[]> getMemoryStandardFilter();

    /**
     * Zwraca dostępne opcje filtrowania po liczbie slotów pamięci.
     *
     * @return lista par [liczba_slotów, liczba_płyt]
     */
    @Query("select m.memorySlots, count(*) from Motherboard m group by m.memorySlots order by m.memorySlots")
    List<Object[]> getMemorySlotFilter();

    /**
     * Zwraca dostępne opcje filtrowania po częstotliwościach pamięci RAM.
     *
     * @return lista par [częstotliwość, liczba_płyt]
     */
    @Query(value = "select distinct unnest(supported_memory_frequencies) as frequency, count(*) from motherboard group by frequency order by frequency", nativeQuery = true)
    List<Object[]> getFrequencyFilter();

    /**
     * Zwraca dostępne opcje filtrowania po obecności modułu Bluetooth.
     *
     * @return lista par [flaga_bluetooth, liczba_płyt]
     */
    @Query("select m.bluetooth, count(*) from Motherboard m group by m.bluetooth order by m.bluetooth")
    List<Object[]> getBluetoothFilter();

    /**
     * Zwraca dostępne opcje filtrowania po obecności modułu WiFi.
     *
     * @return lista par [flaga_wifi, liczba_płyt]
     */
    @Query("select m.wifi, count(*) from Motherboard m group by m.wifi order by m.wifi")
    List<Object[]> getWiFiFilter();

    /**
     * Zwraca dostępne opcje filtrowania po maksymalnej pojemności pamięci.
     *
     * @return lista par [maksymalna_pojemność, liczba_płyt]
     */
    @Query("select m.maxMemoryCapacity, count(*) from Motherboard m group by m.maxMemoryCapacity order by m.maxMemoryCapacity")
    List<Object[]> getMaxMemoryFilter();

    /**
     * Zwraca minimalną szerokość płyty głównej w bazie.
     *
     * @return minimalna szerokość płyty głównej
     */
    @Query("select m.width from Motherboard m order by m.width asc limit 1")
    Double getMinWidth();

    /**
     * Zwraca maksymalną szerokość płyty głównej w bazie.
     *
     * @return maksymalna szerokość płyty głównej
     */
    @Query("select m.width from Motherboard m order by m.width desc limit 1")
    Double getMaxWidth();

    /**
     * Zwraca minimalną głębokość płyty głównej w bazie.
     *
     * @return minimalna głębokość płyty głównej
     */
    @Query("select m.depth from Motherboard m order by m.depth asc limit 1")
    Double getMinDepth();

    /**
     * Zwraca maksymalną głębokość płyty głównej w bazie.
     *
     * @return maksymalna głębokość płyty głównej
     */
    @Query("select m.depth from Motherboard m order by m.depth desc limit 1")
    Double getMaxDepth();
}