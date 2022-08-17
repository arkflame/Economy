package dev._2lstudios.economy.commands.player;

import dev._2lstudios.economy.commands.Argument;
import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.errors.MaxBalanceLimitReachedException;
import dev._2lstudios.economy.errors.MinBalanceLimitReachedException;
import dev._2lstudios.economy.players.EconomyPlayer;

@Command(
    name = "pay",
    arguments = { Argument.OFFLINE_PLAYER, Argument.INT },
    minArguments = 2,
    usageKey = "pay.usage"
)
public class PayCommand extends CommandListener {
    @Override
    public void onExecuteByPlayer(CommandContext ctx) {
        EconomyPlayer player = ctx.getPlayer();
        EconomyPlayer target = ctx.getArguments().getPlayer(0);
        int amount = ctx.getArguments().getInt(1);

        if (target != null) {
            if (!target.isDownloaded()) {
                target.download();
            }

            if (target.getData() != null) {
                try {
                    player.take(amount);
                    target.give(amount);
                    player.sendMessage(
                        player.getI18nMessage("pay.success")
                            .replace("{amount}", String.valueOf(amount))
                            .replace("{player}", target.getData().username)  
                    );
                } catch (MinBalanceLimitReachedException e) {
                    player.sendI18nMessage("pay.not-enough");
                } catch (MaxBalanceLimitReachedException e) {
                    player.sendI18nMessage("pay.target-reached-limit");
                }

                return;
            }
        }

        player.sendI18nMessage("common.player-doesnt-exist");
    }
}
