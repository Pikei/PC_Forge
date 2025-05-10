package com.pc_forge.backend.model.repository.product.cooler;

import com.pc_forge.backend.model.entity.product.cooler.LiquidCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repozytorium/DAO dla układów chłodzenia cieczą, umożliwiające operacje bazodanowe.
 */
public interface LiquidCoolerRepository extends JpaRepository<LiquidCooler, Long> {

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą, kompatybilne z podanym gniazdem CPU.
     *
     * @param socket nazwa gniazda CPU
     * @return lista układów chłodzenia kompatybilnych z podanym gniazdem CPU
     */
    @Query("select l from LiquidCooler l join CoolerSocketCompatibility c on l.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId where s.socketName like :socket")
    List<LiquidCooler> findBySocket(@Param("socket") String socket);

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą o podanej liczbie wentylatorów.
     *
     * @param fans liczba wentylatorów
     * @return lista układów chłodzenia cieczą z podaną liczbą wentylatorów
     */
    List<LiquidCooler> findByFans(Integer fans);

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą o podanej średnicy wentylatorów.
     *
     * @param fanDiameter średnica wentylatora (w mm)
     * @return lista układów chłodzenia cieczą z podaną średnicą wentylatorów
     */
    List<LiquidCooler> findByFanDiameter(Integer fanDiameter);

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą o podanym poziomie hałasu.
     *
     * @param noiseLevel poziom generowanego hałasu
     * @return lista układów chłodzenia cieczą o podanym poziomie hałasu
     */
    List<LiquidCooler> findByNoiseLevel(Double noiseLevel);

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą z podświetleniem lub bez.
     *
     * @param lightning flaga określająca obecność podświetlenia
     * @return lista układów chłodzenia cieczą z danym stanem podświetlenia
     */
    List<LiquidCooler> findByLightning(Boolean lightning);

    /**
     * Kwerenda zwracająca układy chłodzenia cieczą o podanym rozmiarze chłodnicy.
     *
     * @param coolerSize rozmiar chłodnicy (np. 120, 240, 360 mm)
     * @return lista układów chłodzenia cieczą o podanym rozmiarze chłodnicy
     */
    List<LiquidCooler> findByCoolerSize(Integer coolerSize);

    /**
     * Zwraca dostępny filtr gniazd procesora kompatybilnych z układami chłodzenia cieczą.
     *
     * @return lista par [gniazdo_procesora, liczba_układów]
     */
    @Query("select s.socketName, count(*) from LiquidCooler l join CoolerSocketCompatibility c on l.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId group by s.socketName order by s.socketName")
    List<Object[]> getSocketFiler();

    /**
     * Zwraca dostępny filtr liczby wentylatorów w układach chłodzenia cieczą.
     *
     * @return lista par [liczba_wentylatorów, liczba_układów]
     */
    @Query("select l.fans, count(*) from LiquidCooler l group by l.fans order by l.fans")
    List<Object[]> getNumOfFansFiler();

    /**
     * Zwraca dostępny filtr średnic wentylatorów w układach chłodzenia cieczą.
     *
     * @return lista par [średnica_wentylatora, liczba_układów]
     */
    @Query("select l.fanDiameter, count(*) from LiquidCooler l group by l.fanDiameter order by l.fanDiameter")
    List<Object[]> getFanDiameterFiler();

    /**
     * Zwraca dostępny filtr poziomu hałasu układów chłodzenia cieczą.
     *
     * @return lista par [poziom_hałasu, liczba_układów]
     */
    @Query("select l.noiseLevel, count(*) from LiquidCooler l group by l.noiseLevel order by l.noiseLevel")
    List<Object[]> getNoiseLvlFiler();

    /**
     * Zwraca dostępny filtr podświetlenia układów chłodzenia cieczą.
     *
     * @return lista par [podświetlenie, liczba_układów]
     */
    @Query("select l.lightning, count(*) from LiquidCooler l group by l.lightning order by l.lightning")
    List<Object[]> getLightningFiler();

    /**
     * Zwraca dostępny filtr rozmiarów chłodnic układów chłodzenia cieczą.
     *
     * @return lista par [rozmiar_chłodnicy, liczba_układów]
     */
    @Query("select l.coolerSize, count(*) from LiquidCooler l group by l.coolerSize order by l.coolerSize")
    List<Object[]> getCoolerSizeFiler();
}
