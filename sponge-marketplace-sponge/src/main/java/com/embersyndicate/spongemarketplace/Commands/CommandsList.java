package com.embersyndicate.spongemarketplace.Commands;

import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import com.embersyndicate.spongemarketplace.Definations.Permissions;
import com.embersyndicate.spongemarketplace.SpongeMarketplace;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandsList {



    public static CommandSpec Reload = CommandSpec.builder()
            .description(Text.of("Reload Plugin and database."))
            .permission(Permissions.SpongeMarketplaceReload)
            .executor(new reload())
            .build();

    public static  CommandSpec Add = CommandSpec.builder()
            .description(Text.of("Add item to market"))
            .permission(Permissions.SpongeMarketplaceAdd)
            .executor(new add())
            .build();

    public static CommandSpec Remove = CommandSpec.builder()
            .description(Text.of("Remove item from market"))
            .permission(Permissions.SpongeMarketplaceRemove)
            .executor(new remove())
            .build();

    public static CommandSpec BaseCommand = CommandSpec.builder()
            .permission(Permissions.SpongeMarketplaceBase)
            .executor(new base())
            .child(Reload, "reload")
            .child(Add, "add")
            .child(Remove, "remove")
            .build();

    public static void RegisterCommands() {
        final SpongeMarketplace sm = SpongeMarketplace.instance;

        if(ConfigHandler.Aliases.size() == 0 ){
            ConfigHandler.Aliases.add("sm");
            SpongeMarketplace.getLogger().error("Error setting aliases. Check config for Aliases. sm was set as a placeholder.");
        }
        Sponge.getCommandManager().register(sm, CommandsList.BaseCommand, ConfigHandler.Aliases);
    }
}
