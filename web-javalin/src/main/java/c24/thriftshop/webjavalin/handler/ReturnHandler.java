package c24.thriftshop.webjavalin.handler;

import c24.thriftshop.webjavalin.entity.DeviceEntity;
import c24.thriftshop.webjavalin.entity.ReturnRequest;
import c24.thriftshop.webjavalin.entity.ReturnResponse;
import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class ReturnHandler implements Handler {

    DeviceRepository deviceRepository;

    @Inject
    public ReturnHandler(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    private ReturnResponse bookDevice(final DeviceEntity deviceEntity) {
        deviceEntity.setAvailable(true);
        deviceEntity.setUserId("");
        deviceEntity.setRentDate(null);
        deviceEntity.setReturnDate(null);
        deviceRepository.save(deviceEntity);
        return new ReturnResponse("Successful", true);
    }

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final String userId = ctx.attribute("userId");
        final ReturnRequest request = new ObjectMapper().readValue(body, ReturnRequest.class);
        if (deviceRepository.countByDeviceName(request.getDeviceName()) == 1) {
            ctx.json(deviceRepository.findByDeviceName(request.getDeviceName())
                    .filter(deviceEntity -> deviceEntity.getUserId().equals(userId))
                    .map(this::bookDevice)
                    .orElse(new ReturnResponse("Not in rent by this User", false)));
        } else {
            if (request.getDeviceId() == 0) {
                ctx.json(new ReturnResponse("There are multiple Devices with this name pleas add the DeviceId", false));
            } else {
                ctx.json(deviceRepository.findByDeviceNameAndDeviceId(request.getDeviceName(), request.getDeviceId())
                        .filter(deviceEntity -> deviceEntity.getUserId().equals(userId))
                        .map(this::bookDevice)
                        .orElse(new ReturnResponse("Not in rent by this User", false)));
            }
        }
    }
}
