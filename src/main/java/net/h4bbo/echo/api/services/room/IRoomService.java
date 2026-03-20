package net.h4bbo.echo.api.services.room;

import net.h4bbo.echo.storage.models.room.RoomData;
import net.h4bbo.echo.storage.views.room.RoomDetailsView;
import org.oldskooler.entity4j.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IRoomService {
    public RoomDetailsView getRoom(int roomId);

    List<RoomDetailsView> getRoomsByCategory(int categoryId);

    List<RoomDetailsView> getRoomsByUserId(int userId);

    void saveRoomSlots(int roomId, int slots);

    List<RoomDetailsView> getRooms(Consumer<Query.Filters<RoomDetailsView>> predicate);
}
