package com.pc_forge.backend.model.repository.product.drive;

import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO dla dysków SSD, umożliwiające operacje bazodanowe.
 */
public interface SolidStateDriveRepository extends JpaRepository<SolidStateDrive, Long> {

    /**
     * Kwerenda znajdująca dyski SSD według formatu.
     *
     * @param driveFormat format dysku (np. 2.5", M.2)
     * @return lista dysków SSD o podanym formacie
     */
    List<SolidStateDrive> findByDriveFormat(String driveFormat);

    /**
     * Kwerenda znajdująca dyski SSD według interfejsu.
     *
     * @param driveInterface interfejs dysku (np. SATA, NVMe)
     * @return lista dysków SSD o podanym interfejsie
     */
    List<SolidStateDrive> findByDriveInterface(String driveInterface);

    /**
     * Kwerenda znajdująca dyski SSD według pojemności.
     *
     * @param storage pojemność dysku (w GB)
     * @return lista dysków SSD o podanej pojemności
     */
    List<SolidStateDrive> findByStorage(Integer storage);

    /**
     * Kwerenda znajdująca dyski SSD według prędkości odczytu.
     *
     * @param readSpeed prędkość odczytu (w MB/s)
     * @return lista dysków SSD o podanej prędkości odczytu
     */
    List<SolidStateDrive> findByReadSpeed(Integer readSpeed);

    /**
     * Kwerenda znajdująca dyski SSD według prędkości zapisu.
     *
     * @param writeSpeed prędkość zapisu (w MB/s)
     * @return lista dysków SSD o podanej prędkości zapisu
     */
    List<SolidStateDrive> findByWriteSpeed(Integer writeSpeed);

    /**
     * Zwraca dostępny filtr formatów dysków SSD.
     *
     * @return lista par [format_dysku, liczba_dysków]
     */
    @Query("select s.driveFormat, count(*) from SolidStateDrive s group by s.driveFormat order by s.driveFormat")
    List<Object[]> getFormatFilter();

    /**
     * Zwraca dostępny filtr interfejsów dysków SSD.
     *
     * @return lista par [interfejs_dysku, liczba_dysków]
     */
    @Query("select s.driveInterface, count(*) from SolidStateDrive s group by s.driveInterface order by s.driveInterface")
    List<Object[]> getInterfaceFilter();

    /**
     * Zwraca dostępny filtr pojemności dysków SSD.
     *
     * @return lista par [pojemność, liczba_dysków]
     */
    @Query("select s.storage, count(*) from SolidStateDrive s group by s.storage order by s.storage")
    List<Object[]> getStorageFilter();

    /**
     * Zwraca dostępny filtr prędkości odczytu dysków SSD.
     *
     * @return lista par [prędkość_odczytu, liczba_dysków]
     */
    @Query("select s.readSpeed, count(*) from SolidStateDrive s group by s.readSpeed order by s.readSpeed")
    List<Object[]> getReadSpeedFilter();

    /**
     * Zwraca dostępny filtr prędkości zapisu dysków SSD.
     *
     * @return lista par [prędkość_zapisu, liczba_dysków]
     */
    @Query("select s.writeSpeed, count(*) from SolidStateDrive s group by s.writeSpeed order by s.writeSpeed")
    List<Object[]> getWriteSpeedFilter();
}