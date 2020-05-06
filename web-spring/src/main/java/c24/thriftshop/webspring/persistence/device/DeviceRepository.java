package c24.thriftshop.webspring.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, UUID> {

    Optional<DeviceEntity> findByName(String name);
}