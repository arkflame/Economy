package dev._2lstudios.economy.commands.impl;

import dev._2lstudios.economy.commands.Command;
import dev._2lstudios.economy.commands.CommandContext;
import dev._2lstudios.economy.commands.CommandListener;

@Command(
  name = "hello"
)
public class HelloCommand extends CommandListener {
  public HelloCommand() {
    this.addSubcommand(new WorldSubCommand());
  }

  @Override
  public void onExecute(CommandContext ctx) {
    ctx.getExecutor().sendI18nMessage("hello");
  }
}
