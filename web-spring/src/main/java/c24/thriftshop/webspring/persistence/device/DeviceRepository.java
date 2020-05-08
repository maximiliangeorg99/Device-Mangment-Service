package c24.thriftshop.webspring.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {

    Optional<DeviceEntity> findByDeviceName(String name);

    Optional<DeviceEntity> findByDeviceNameAndDeviceId(String name, int id);

    int countByDeviceName(String name);

    List<DeviceEntity> findAllByDeviceName(String name);
}