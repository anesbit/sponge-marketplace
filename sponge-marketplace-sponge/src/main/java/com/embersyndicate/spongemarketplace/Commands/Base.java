package com.embersyndicate.spongemarketplace.Commands;

import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.ArrayList;
import java.util.List;

public class Base implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        List<Text> texts = new ArrayList<>();
        String prefix = ConfigHandler.PluginPrefix;
        if (prefix == null) {
            throw new CommandException(Text.of(TextColors.RED, "Error sending pluginPrefix. It's somehow null?!? Check your config file!"));
        }
        texts.add(Text.builder()
                .append(Text.builder()
                        .append(Text.of(TextColors.WHITE, "Votes left until a "))
                        .append(Text.of(TextColors.WHITE, TextSerializers.FORMATTING_CODE.deserialize(prefix).toPlain() + ": "))
                        .append(Text.of(TextColors.AQUA, RequiredVotes.getVotesLeft()))
                        .build())
                .build());
        if (src.hasPermission(Permissions.BaconPartyFakeVote)) {
            texts.add(Text.builder()
                    .append(Text.builder()
                            .color(TextColors.WHITE)
                            .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " fakevote"))
                            .onClick(TextActions.runCommand("/" + ConfigHandler.Aliases.get(0) + " fakevote"))
                            .onHover(TextActions.showText(Text.of("Click here fake vote once! ")))
                            .build())
                    .build());
        }
        if (src.hasPermission(Permissions.BaconPartyReload)) {
            texts.add(Text.builder()
                    .append(Text.builder()
                            .color(TextColors.WHITE)
                            .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " reload"))
                            .onClick(TextActions.runCommand("/" + ConfigHandler.Aliases.get(0) + " reload"))
                            .onHover(TextActions.showText(Text.of("Click here reload the config! ")))
                            .build())
                    .build());
        }
        if (src.hasPermission(Permissions.BaconPartyForce)) {
            texts.add(Text.builder()
                    .append(Text.builder()
                            .color(TextColors.WHITE)
                            .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " force"))
                            .onClick(TextActions.runCommand("/" + ConfigHandler.Aliases.get(0) + " force"))
                            .onHover(TextActions.showText(Text.of("Click here force start a " + TextSerializers.FORMATTING_CODE.deserialize(prefix).toPlain() + "!")))
                            .build())
                    .build());
        }
        PaginationList paginationlist = new Pagination().getPaginationService().builder().footer(Text.of(TextColors.WHITE, "Made by kristi71111")).padding(Text.of("-")).title(Text.builder()
                .append(Text.builder()
                        .append(Text.of(TextColors.GOLD, TextStyles.BOLD, "{"))
                        .append(Text.of(TextColors.WHITE, TextStyles.BOLD, TextSerializers.FORMATTING_CODE.deserialize(prefix).toPlain()))
                        .append(Text.of(TextColors.GOLD, TextStyles.BOLD, "}"))
                        .build())
                .build()).contents(texts).build();
        paginationlist.sendTo(src);
        return CommandResult.success();
    }
}
