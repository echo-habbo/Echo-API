package net.h4bbo.echo.api.services.navigator;

import net.h4bbo.echo.storage.models.navigator.NavigatorCategoryData;
import net.h4bbo.echo.storage.models.room.RoomData;
import net.h4bbo.echo.storage.views.room.RoomDetailsView;

import java.util.List;

public interface INavigatorService {
    public List<NavigatorCategoryData> getCategories();

    List<RoomDetailsView> search(String queryString);
}
