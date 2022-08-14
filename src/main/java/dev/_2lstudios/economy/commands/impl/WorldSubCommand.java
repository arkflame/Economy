package dev._2lstudios.economy.commands.impl;

import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandListener;

@Command(
  name = "world"
)
public class WorldSubCommand extends CommandListener {
    @Override
    public void onExecute(CommandContext ctx) {
        ctx.getExecutor().sendI18nMessage("world");
    }
}
