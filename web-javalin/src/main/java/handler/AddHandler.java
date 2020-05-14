package handler;

import entity.DeviceEntity;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import persistence.DeviceRepository;

import java.util.UUID;

public class AddHandler implements Handler {
    DeviceRepository deviceRepository;

    //Autowire
    public AddHandler(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final JSONObject jsonObject = new JSONObject(body);
        final int userId = deviceRepository.countByDeviceName(jsonObject.getString("name")) + 1;
        final DeviceEntity deviceEntity = new DeviceEntity(UUID.randomUUID(), jsonObject.getString("name"), userId, jsonObject.getString("description"), true, "", null, null);
        deviceRepository.save(deviceEntity);
    }
}