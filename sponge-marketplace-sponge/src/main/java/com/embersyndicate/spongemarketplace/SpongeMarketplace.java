package com.embersyndicate.spongemarketplace;

import com.embersyndicate.spongemarketplace.Commands.CommandsList;
import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStoppedEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;
@Plugin(
        id = "sponge-marketplace",
        name = "Sponge Marketplace",
        version = "@VERSION@",
        description = "A GUI based marketplace for all of SPONGE!"
)

public class SpongeMarketplace {

    public static SpongeMarketplace instance;
    //public static String version = "@VERSION@";

    @Inject private Logger logger;

    @Inject @ConfigDir(sharedRoot = true)
    private File defaultConfigDir;

    @Inject
    public Game game;




    public static Logger getLogger() {
        return getInstance().logger;
    }

    public static SpongeMarketplace getInstance(){
        return instance;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        instance = this;
        File rootDir = new File(defaultConfigDir, "sponge-marketplace");
        ConfigHandler.init(rootDir);
        CommandsList.RegisterCommands();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Sponge Marketplace is running on version "+ "@VERSION@" + ".");
        ConfigHandler.load();
        ConfigHandler.loadValues();

    }
    @Listener
    public void onServerStop(GameStoppedEvent event) {
        logger.info("Sponge Marketplace Stopping!");
    }
}
