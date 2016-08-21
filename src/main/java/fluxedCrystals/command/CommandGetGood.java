package fluxedCrystals.command;

import fluxedCrystals.reference.Names;
import fluxedCrystals.util.SeedEditor;
import net.minecraft.command.*;

import java.util.List;

public class CommandGetGood extends CommandBase
{

	@Override
	public String getCommandName () {
		return Names.Commands.GET_GOOD;
	}

	@Override
	public int getRequiredPermissionLevel () {
		//TODO figure out what permissionLevel it is for admin/OPS
		return 2;
	}

	@Override
	public String getCommandUsage (ICommandSender commandSender) {

		return "it doesnt take a argument, GIT GUD";

	}

	@Override
	public void processCommand (ICommandSender commandSender, String[] args) {

		if (args.length < 1) {

			throw new WrongUsageException("NO-OP");

		}
		else {
			if (!commandSender.getEntityWorld().isRemote) {
				//TODO actually message a server
			}
		}

	}

	@Override
	public List addTabCompletionOptions (ICommandSender commandSender, String[] args) {

		return null;

	}

}
