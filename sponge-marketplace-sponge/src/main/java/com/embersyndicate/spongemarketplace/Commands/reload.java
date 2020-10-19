package com.embersyndicate.spongemarketplace.Commands;

import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.serializer.TextSerializers;

public class reload implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String prefix = ConfigHandler.pluginPrefix;
        if (prefix == null) {
            throw new CommandException(Text.of(TextColors.RED, "Error sending plugin prefix. Check config file for Plugin Prefix."));
        }
        //RELOAD CONFIG
        ConfigHandler.load();
        ConfigHandler.loadValues();

        //RELOAD DATABASE

        src.sendMessage(Text.of(TextColors.BLUE, TextStyles.BOLD, TextSerializers.FORMATTING_CODE.deserialize(prefix).toPlain() + "'s config reloaded"));
        return CommandResult.success();
    }
}
