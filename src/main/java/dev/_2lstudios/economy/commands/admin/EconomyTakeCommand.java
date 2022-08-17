package dev._2lstudios.economy.commands.admin;

import dev._2lstudios.economy.commands.Argument;
import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandExecutor;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.errors.MinBalanceLimitReachedException;
import dev._2lstudios.economy.players.EconomyPlayer;

@Command(
    name = "take",
    arguments = { Argument.OFFLINE_PLAYER, Argument.INT },
    minArguments = 2,
    usageKey = "economy.take.usage"
)
public class EconomyTakeCommand extends CommandListener {
    @Override
    public void onExecute(CommandContext ctx) {
        CommandExecutor executor = ctx.getExecutor();
        EconomyPlayer player = ctx.getArguments().getPlayer(0);
        int amount = ctx.getArguments().getInt(1);

        if (player != null) {
            player.download();

            if (player.getData() != null) {
                try {
                    double balance = player.take(amount);
                    executor.sendMessage(
                        executor.getI18nMessage("economy.take.success")
                            .replace("{player}", player.getName())
                            .replace("{amount}", String.valueOf(amount))
                            .replace("{balance}", String.valueOf(balance))
                    );
                } catch (MinBalanceLimitReachedException e) {
                    executor.sendI18nMessage("common.min-limit-reached");
                }

                return;
            }
        }

        executor.sendI18nMessage("common.player-doesnt-exist");
    }
}
