package c24.thriftshop.webjavalin.handler;

import c24.thriftshop.webjavalin.entity.DeviceEntity;
import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;

public class AddHandler implements Handler {

    DeviceRepository deviceRepository;

    @Inject
    public AddHandler(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final JSONObject jsonObject = new JSONObject(body);
        final DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceName(jsonObject.getString("name"));
        deviceEntity.setUserId("");
        deviceEntity.setDeviceDescription(jsonObject.getString("description"));
        deviceEntity.setAvailable(true);
        deviceEntity.setRentDate(null);
        deviceEntity.setReturnDate(null);
        deviceRepository.save(deviceEntity);
    }
}