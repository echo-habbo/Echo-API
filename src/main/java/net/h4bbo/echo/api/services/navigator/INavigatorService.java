package net.h4bbo.echo.api.services.navigator;

import net.h4bbo.echo.storage.models.navigator.NavigatorCategoryData;
import net.h4bbo.echo.storage.models.room.RoomData;

import java.util.List;

public interface INavigatorService {
    public List<NavigatorCategoryData> getCategories();

    List<RoomData> search(String queryString);
}
