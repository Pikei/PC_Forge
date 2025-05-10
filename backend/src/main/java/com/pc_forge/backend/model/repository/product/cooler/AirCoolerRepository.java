package com.pc_forge.backend.model.repository.product.cooler;

import com.pc_forge.backend.model.entity.product.cooler.AirCooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repozytorium/DAO układu chłodzenia powietrzem do operacji bazodanowych.
 */
public interface AirCoolerRepository extends JpaRepository<AirCooler, Long> {

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem, kompatybilne z podanym gniazdem CPU.
     *
     * @param socket nazwa gniazda CPU
     * @return lista układów chłodzenia kompatybilnych z danym gniazdem CPU
     */
    @Query("select a from AirCooler a join CoolerSocketCompatibility c on a.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId where s.socketName like :socket")
    List<AirCooler> findBySocket(@Param("socket") String socket);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o podanej liczbie wentylatorów.
     *
     * @param fans liczba wentylatorów
     * @return lista układów chłodzenia z podaną liczbą wentylatorów
     */
    List<AirCooler> findByFans(Integer fans);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o podanej średnicy wentylatorów.
     *
     * @param fanDiameter średnica wentylatora (w mm)
     * @return lista układów chłodzenia z podaną średnicą wentylatorów
     */
    List<AirCooler> findByFanDiameter(Integer fanDiameter);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o podanym poziomie hałasu.
     *
     * @param noiseLevel poziom generowanego hałasu
     * @return lista układów chłodzenia o podanym poziomie hałasu
     */
    List<AirCooler> findByNoiseLevel(Double noiseLevel);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem z podświetleniem lub bez.
     *
     * @param lightning flaga określająca obecność podświetlenia
     * @return lista układów chłodzenia z danym stanem podświetlenia
     */
    List<AirCooler> findByLightning(Boolean lightning);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o podanym materiale podstawy.
     *
     * @param baseMaterial materiał podstawy (np. aluminium, miedź)
     * @return lista układów chłodzenia z podanym materiałem podstawy
     */
    List<AirCooler> findByBaseMaterial(String baseMaterial);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o szerokości w podanym zakresie.
     *
     * @param widthStart minimalna szerokość układu chłodzenia (w mm)
     * @param widthEnd   maksymalna szerokość układu chłodzenia (w mm)
     * @return lista układów chłodzenia o szerokości w podanym zakresie
     */
    List<AirCooler> findByWidthBetween(Integer widthStart, Integer widthEnd);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o wysokości w podanym zakresie.
     *
     * @param heightStart minimalna wysokość układu chłodzenia (w mm)
     * @param heightEnd   maksymalna wysokość układu chłodzenia (w mm)
     * @return lista układów chłodzenia o wysokości w podanym zakresie
     */
    List<AirCooler> findByHeightBetween(Integer heightStart, Integer heightEnd);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o głębokości w podanym zakresie.
     *
     * @param depthStart minimalna głębokość układu chłodzenia (w mm)
     * @param depthEnd   maksymalna głębokość układu chłodzenia (w mm)
     * @return lista układów chłodzenia o głębokości w podanym zakresie
     */
    List<AirCooler> findByDepthBetween(Integer depthStart, Integer depthEnd);

    /**
     * Kwerenda znajdująca układy chłodzenia powietrzem o określonym sposobie instalacji wertykalnej.
     *
     * @param verticalInstallation flaga określająca obecność instalacji pionowej
     * @return lista układów chłodzenia z danym sposobem instalacji
     */
    List<AirCooler> findByVerticalInstallation(Boolean verticalInstallation);

    /**
     * Zwraca minimalną szerokość dostępnych układów chłodzenia.
     *
     * @return minimalna szerokość układu chłodzenia (w mm)
     */
    @Query("select a.width from AirCooler a order by a.width asc limit 1")
    Integer getMinWidth();

    /**
     * Zwraca maksymalną szerokość dostępnych układów chłodzenia.
     *
     * @return maksymalna szerokość układu chłodzenia (w mm)
     */
    @Query("select a.width from AirCooler a order by a.width desc limit 1")
    Integer getMaxWidth();

    /**
     * Zwraca minimalną wysokość dostępnych układów chłodzenia.
     *
     * @return minimalna wysokość układu chłodzenia (w mm)
     */
    @Query("select a.height from AirCooler a order by a.height asc limit 1")
    Integer getMinHeight();

    /**
     * Zwraca maksymalną wysokość dostępnych układów chłodzenia.
     *
     * @return maksymalna wysokość układu chłodzenia (w mm)
     */
    @Query("select a.height from AirCooler a order by a.height desc limit 1")
    Integer getMaxHeight();

    /**
     * Zwraca minimalną głębokość dostępnych układów chłodzenia.
     *
     * @return minimalna głębokość układu chłodzenia (w mm)
     */
    @Query("select a.depth from AirCooler a order by a.depth asc limit 1")
    Integer getMinDepth();

    /**
     * Zwraca maksymalną głębokość dostępnych układów chłodzenia.
     *
     * @return maksymalna głębokość układu chłodzenia (w mm)
     */
    @Query("select a.depth from AirCooler a order by a.depth desc limit 1")
    Integer getMaxDepth();

    /**
     * Zwraca dostępny filtr gniazd procesora kompatybilnych z układami chłodzenia.
     *
     * @return lista par [gniazdo_procesora, liczba_układów]
     */
    @Query("select s.socketName, count(*) from AirCooler a join CoolerSocketCompatibility c on a.id = c.ean.id join CpuSocket s on c.socket.socketId = s.socketId group by s.socketName order by s.socketName")
    List<Object[]> getSocketFiler();

    /**
     * Zwraca dostępny filtr liczby wentylatorów w układach chłodzenia.
     *
     * @return lista par [liczba_wentylatorów, liczba_układów]
     */
    @Query("select a.fans, count(*) from AirCooler a group by a.fans order by a.fans")
    List<Object[]> getNumOfFansFiler();

    /**
     * Zwraca dostępny filtr średnic wentylatorów w układach chłodzenia.
     *
     * @return lista par [średnica, liczba_układów]
     */
    @Query("select a.fanDiameter, count(*) from AirCooler a group by a.fanDiameter order by a.fanDiameter")
    List<Object[]> getFanDiameterFiler();

    /**
     * Zwraca dostępny filtr poziomu hałasu układów chłodzenia.
     *
     * @return lista par [poziom_hałasu, liczba_układów]
     */
    @Query("select a.noiseLevel, count(*) from AirCooler a group by a.noiseLevel order by a.noiseLevel")
    List<Object[]> getNoiseLvlFiler();

    /**
     * Zwraca dostępny filtr podświetlenia układów chłodzenia.
     *
     * @return lista par [podświetlenie, liczba_układów]
     */
    @Query("select a.lightning, count(*) from AirCooler a group by a.lightning order by a.lightning")
    List<Object[]> getLightningFiler();

    /**
     * Zwraca dostępny filtr materiałów podstawy w układach chłodzenia.
     *
     * @return lista par [materiał_podstawy, liczba_układów]
     */
    @Query("select a.baseMaterial, count(*) from AirCooler a group by a.baseMaterial order by a.baseMaterial")
    List<Object[]> getBaseMaterialFiler();

    /**
     * Zwraca dostępny filtr sposobów instalacji układów chłodzenia.
     *
     * @return lista par [instalacja_wertykalna, liczba_układów]
     */
    @Query("select a.verticalInstallation, count(*) from AirCooler a group by a.verticalInstallation order by a.verticalInstallation")
    List<Object[]> getVerticalInstallationFiler();

}