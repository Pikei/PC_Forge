package com.pc_forge.backend.model.product.repository;

import com.pc_forge.backend.model.product.HardDiskDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HardDiskDriveRepository extends JpaRepository<HardDiskDrive, Long> {

    List<HardDiskDrive> findByDriveFormat(String driveFormat);

    List<HardDiskDrive> findByDriveInterface(String driveInterface);

    List<HardDiskDrive> findByStorage(Integer storage);

    List<HardDiskDrive> findByRotationalSpeed(Integer rotationalSpeed);


    @Query("select s.driveFormat, count(*) from HardDiskDrive s group by s.driveFormat order by s.driveFormat")
    List<Object[]> getFormatFilter();

    @Query("select s.driveInterface, count(*) from HardDiskDrive s group by s.driveInterface order by s.driveInterface")
    List<Object[]> getInterfaceFilter();

    @Query("select s.storage, count(*) from HardDiskDrive s group by s.storage order by s.storage")
    List<Object[]> getStorageFilter();

    @Query("select s.rotationalSpeed, count(*) from HardDiskDrive s group by s.rotationalSpeed order by s.rotationalSpeed")
    List<Object[]> getReadSpeedFilter();
}