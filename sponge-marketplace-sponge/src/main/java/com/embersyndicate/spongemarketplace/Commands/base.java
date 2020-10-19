package com.embersyndicate.spongemarketplace.Commands;

import com.embersyndicate.spongemarketplace.Config.ConfigHandler;
import com.embersyndicate.spongemarketplace.Definations.Pagination;
import com.embersyndicate.spongemarketplace.Definations.Permissions;
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

public class base implements CommandExecutor {

    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        List<Text> texts = new ArrayList<>();
        String prefix = ConfigHandler.pluginPrefix;
        if(prefix == null) {
            throw new CommandException(Text.of(TextColors.RED, "Error sending prefix. Check config for Plugin Prefix."));
        }

        texts.add(Text.builder()
                    .append(Text.of(TextColors.BLUE, "========"))
                    .append(Text.of(TextColors.WHITE, "Sponge Marketplace"))
                    .append(Text.of(TextColors.BLUE, "========"))
                    .build());

        if (src.hasPermission(Permissions.SpongeMarketplaceReload)) {
            texts.add(Text.builder()
                    .append(Text.builder()
                        .color(TextColors.WHITE)
                        .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " reload"))
                        .onClick(TextActions.suggestCommand("/" + ConfigHandler.Aliases.get(0) + " reload"))
                        .onHover(TextActions.showText(Text.of("Click to reload config! ")))
                        .build())
                    .build());
        }

        if (src.hasPermission(Permissions.SpongeMarketplaceAdd)) {
            texts.add(Text.builder()
                .append(Text.builder()
                    .color(TextColors.WHITE)
                    .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " sell <value>"))
                    .onClick(TextActions.suggestCommand("/" + ConfigHandler.Aliases.get(0) + " add "))
                    .onHover(TextActions.showText(Text.of( "Ussage: /" + ConfigHandler.Aliases.get(0) + " add <value>")))
                    .build())
                .build());
        }

        if (src.hasPermission(Permissions.SpongeMarketplaceRemove)) {
            texts.add(Text.builder()
                .append(Text.builder()
                    .color(TextColors.WHITE)
                    .append(Text.of("/" + ConfigHandler.Aliases.get(0) + " remove <id>"))
                    .onClick(TextActions.suggestCommand("/" + ConfigHandler.Aliases.get(0) + " remove "))
                    .onHover(TextActions.showText(Text.of("/" + ConfigHandler.Aliases.get(0) + " remove <id>")))
                    .build())
                .build());
        }

        PaginationList paginationList = new Pagination().getPaginationService().builder().footer(Text.of(TextColors.WHITE, "Made by DJDeath")).padding(Text.of("-")).title(Text.builder()
                .append(Text.builder()
                    .append(Text.of(TextColors.BLUE, TextStyles.BOLD, "{"))
                    .append(Text.of(TextColors.WHITE, TextStyles.BOLD, TextSerializers.FORMATTING_CODE.deserialize(prefix).toPlain()))
                    .append(Text.of(TextColors.BLUE, TextStyles.BOLD, "}"))
                .build())
        .build()).contents(texts).build();
        paginationList.sendTo(src);
        return CommandResult.success();
        }
}
