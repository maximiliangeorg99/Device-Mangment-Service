package c24.thriftshop.webjavalin.handler;

import c24.thriftshop.webjavalin.entity.DeviceEntity;
import c24.thriftshop.webjavalin.entity.Devices;
import c24.thriftshop.webjavalin.entity.RentRequest;
import c24.thriftshop.webjavalin.entity.RentResponse;
import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class RentHandler implements Handler {

    DeviceRepository deviceRepository;

    @Inject
    public RentHandler(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    private Date addHours(final Date date, final int hours) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    private RentResponse bookDevice(final DeviceEntity deviceEntity,
                                    final RentRequest request, final String userId) {
        if (!deviceEntity.isAvailable()) {
            return new RentResponse("In rent until " + deviceEntity.getReturnDate().toString(), false);
        }
        deviceEntity.setAvailable(false);
        deviceEntity.setUserId(userId);
        deviceEntity.setRentDate(new Date());
        deviceEntity.setReturnDate(addHours(new Date(), request.getDurationInHours()));
        deviceRepository.save(deviceEntity);
        return new RentResponse("Successful", true);
    }

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final RentRequest request = new ObjectMapper().readValue(body, RentRequest.class);
        final String userId = ctx.attribute("userId");
        final int deviceCount = deviceRepository.countByDeviceName(request.getDeviceName());
        if (deviceCount == 0) {
            ctx.json(new RentResponse("No such device", false));
        } else if (deviceCount == 1) {
            final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByDeviceName(request.getDeviceName());
            ctx.json(bookDevice(optionalDeviceEntity.get(), request, userId));
        } else {
            if (request.getDeviceId() == 0) {
                ctx.json(new RentResponse("There are multiple Devices with this name pleas add the DeviceId", false, new Devices((ArrayList<DeviceEntity>) deviceRepository.findAllByDeviceName(request.getDeviceName()))));
            } else {
                final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByDeviceNameAndDeviceId(request.getDeviceName(), request.getDeviceId());
                if (optionalDeviceEntity.isPresent()) {
                    ctx.json(bookDevice(optionalDeviceEntity.get(), request, userId));
                } else {
                    ctx.json(new RentResponse("No device with this deviceName and deviceId", false));
                }
            }
        }
    }
}
