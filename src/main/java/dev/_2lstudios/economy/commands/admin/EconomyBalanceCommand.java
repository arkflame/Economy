package dev._2lstudios.economy.commands.admin;

import dev._2lstudios.economy.commands.Argument;
import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandExecutor;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.players.EconomyPlayer;

@Command(
    name = "balance",
    arguments = { Argument.OFFLINE_PLAYER },
    minArguments = 1,
    usageKey = "economy.balance.usage"
)
public class EconomyBalanceCommand extends CommandListener {
    @Override
    public void onExecute(CommandContext ctx) {
        CommandExecutor executor = ctx.getExecutor();
        EconomyPlayer player = ctx.getArguments().getPlayer(0);

        if (player != null) {
            player.download();

            if (player.getData() != null) {
                executor.sendMessage(
                    executor.getI18nMessage("economy.balance.success")
                        .replace("{player}", player.getName())
                        .replace("{balance}", String.valueOf(player.getBalance()))
                );

                return;
            }
        }

        executor.sendI18nMessage("common.player-doesnt-exist");
    }
}
