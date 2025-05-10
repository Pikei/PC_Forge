package com.pc_forge.backend.model.repository.product.drive;

import com.pc_forge.backend.model.entity.product.drive.HardDiskDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repozytorium/DAO dla dysków twardych (HDD), umożliwiające operacje bazodanowe.
 */
public interface HardDiskDriveRepository extends JpaRepository<HardDiskDrive, Long> {

    /**
     * Kwerenda znajdująca dyski twarde według formatu dysku.
     *
     * @param driveFormat format dysku (np. 2.5", 3.5")
     * @return lista dysków o podanym formacie
     */
    List<HardDiskDrive> findByDriveFormat(String driveFormat);

    /**
     * Kwerenda znajdująca dyski twarde według interfejsu dysku.
     *
     * @param driveInterface interfejs dysku (np. SATA, SAS)
     * @return lista dysków o podanym interfejsie
     */
    List<HardDiskDrive> findByDriveInterface(String driveInterface);

    /**
     * Kwerenda znajdująca dyski twarde według pojemności.
     *
     * @param storage pojemność dysku (w GB)
     * @return lista dysków o podanej pojemności
     */
    List<HardDiskDrive> findByStorage(Integer storage);

    /**
     * Kwerenda znajdująca dyski twarde według prędkości obrotowej.
     *
     * @param rotationalSpeed prędkość obrotowa dysku (w RPM)
     * @return lista dysków o podanej prędkości obrotowej
     */
    List<HardDiskDrive> findByRotationalSpeed(Integer rotationalSpeed);

    /**
     * Zwraca dostępny filtr formatów dysków.
     *
     * @return lista par [format_dysku, liczba_dysków]
     */
    @Query("select s.driveFormat, count(*) from HardDiskDrive s group by s.driveFormat order by s.driveFormat")
    List<Object[]> getFormatFilter();

    /**
     * Zwraca dostępny filtr interfejsów dysków.
     *
     * @return lista par [interfejs_dysku, liczba_dysków]
     */
    @Query("select s.driveInterface, count(*) from HardDiskDrive s group by s.driveInterface order by s.driveInterface")
    List<Object[]> getInterfaceFilter();

    /**
     * Zwraca dostępny filtr pojemności dysków.
     *
     * @return lista par [pojemność, liczba_dysków]
     */
    @Query("select s.storage, count(*) from HardDiskDrive s group by s.storage order by s.storage")
    List<Object[]> getStorageFilter();

    /**
     * Zwraca dostępny filtr prędkości obrotowych dysków.
     *
     * @return lista par [prędkość_obrotowa, liczba_dysków]
     */
    @Query("select s.rotationalSpeed, count(*) from HardDiskDrive s group by s.rotationalSpeed order by s.rotationalSpeed")
    List<Object[]> getReadSpeedFilter();
}