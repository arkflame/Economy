package dev._2lstudios.economy.commands.player;

import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandListener;
import dev._2lstudios.economy.players.EconomyPlayer;

@Command(name = "balance")
public class HistoryCommand extends CommandListener {
    public void sendPage(EconomyPlayer player, int page) {
        int pageCount = 1;

        if (page <= pageCount || page <= 0) {

        } else {
            player.sendI18nMessage("history.invalid-page");
        }
    }

    @Override
    public void onExecuteByPlayer(CommandContext ctx) {
        EconomyPlayer player = ctx.getPlayer();
        player.sendI18nMessage("balance.success",
                    new String[] { "{balance}", String.valueOf(player.getBalance()) });
    }
}
