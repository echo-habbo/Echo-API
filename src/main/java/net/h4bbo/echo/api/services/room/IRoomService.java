package net.h4bbo.echo.api.services.room;

import net.h4bbo.echo.storage.models.room.RoomData;
import org.oldskooler.entity4j.Query;

import java.util.List;
import java.util.function.Function;

public interface IRoomService {
    public RoomData getRoom(int roomId);

    List<RoomData> getRoomsByCategory(int categoryId);

    List<RoomData> getRoomsByUserId(int userId);

    void saveRoomSlots(int roomId, int slots);

    List<RoomData> getRooms(Function<Query.Filters<RoomData>, Query.Filters<RoomData>> predicate);
}
