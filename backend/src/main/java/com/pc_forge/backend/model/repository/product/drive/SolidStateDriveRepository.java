package com.pc_forge.backend.model.repository.product.drive;

import com.pc_forge.backend.model.entity.product.drive.SolidStateDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolidStateDriveRepository extends JpaRepository<SolidStateDrive, Long> {
    List<SolidStateDrive> findByDriveFormat(String driveFormat);

    List<SolidStateDrive> findByDriveInterface(String driveInterface);

    List<SolidStateDrive> findByStorage(Integer storage);

    List<SolidStateDrive> findByReadSpeed(Integer readSpeed);

    List<SolidStateDrive> findByWriteSpeed(Integer writeSpeed);

    @Query("select s.driveFormat, count(*) from SolidStateDrive s group by s.driveFormat order by s.driveFormat")
    List<Object[]> getFormatFilter();

    @Query("select s.driveInterface, count(*) from SolidStateDrive s group by s.driveInterface order by s.driveInterface")
    List<Object[]> getInterfaceFilter();

    @Query("select s.storage, count(*) from SolidStateDrive s group by s.storage order by s.storage")
    List<Object[]> getStorageFilter();

    @Query("select s.readSpeed, count(*) from SolidStateDrive s group by s.readSpeed order by s.readSpeed")
    List<Object[]> getReadSpeedFilter();

    @Query("select s.writeSpeed, count(*) from SolidStateDrive s group by s.writeSpeed order by s.writeSpeed")
    List<Object[]> getWriteSpeedFilter();

}