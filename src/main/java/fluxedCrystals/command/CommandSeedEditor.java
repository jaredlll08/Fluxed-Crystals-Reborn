package fluxedCrystals.command;

import fluxedCrystals.reference.Names;
import fluxedCrystals.util.SeedEditor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

import java.util.List;

public class CommandSeedEditor extends CommandBase {

	@Override
	public String getCommandName() {
		return Names.Commands.SEED_EDITOR;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender commandSender) {

		return "NO-OP";

	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) {

		if (args.length < 1) {

			throw new WrongUsageException("NO-OP");

		} else {
			if (!commandSender.getEntityWorld().isRemote) {
				SeedEditor.start();
			}
		}

	}

	@Override
	public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {

		return null;

	}

}
