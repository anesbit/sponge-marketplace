package com.embersyndicate.spongemarketplace.Definations;

import com.embersyndicate.spongemarketplace.SpongeMarketplace;
import org.spongepowered.api.Game;
import org.spongepowered.api.service.pagination.PaginationService;

public class Pagination {
    final SpongeMarketplace pl = SpongeMarketplace.instance;
    final Game game = pl.game;

    //Pagination service builder
    public PaginationService getPaginationService() {
        if( game.getServiceManager().provide(PaginationService.class).isPresent()) {
            return game.getServiceManager().provide(PaginationService.class).get();
        } else {
            return null;
        }
    }
}
