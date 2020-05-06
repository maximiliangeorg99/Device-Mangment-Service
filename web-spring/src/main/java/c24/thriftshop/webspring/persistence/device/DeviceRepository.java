package c24.thriftshop.webspring.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE DeviceEntity d SET d.available = ?1 WHERE d.id = ?2")
    void updateDeviceAvailability(boolean availablem, UUID id);

    Optional<DeviceEntity> findByName(String name);
}