package dev._2lstudios.economy.commands.admin;

import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandListener;

@Command(
  name = "economy"
)
public class EconomyCommand extends CommandListener {
  public EconomyCommand() {
    this.addSubcommand(new EconomyBalanceCommand());
    this.addSubcommand(new EconomyGiveBalance());
    this.addSubcommand(new EconomyTakeCommand());
  }

  @Override
  public void onExecute(CommandContext ctx) {
    ctx.getExecutor().sendI18nMessage("economy.help");
  }
}
