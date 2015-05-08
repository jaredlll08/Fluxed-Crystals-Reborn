package fluxedCrystals.command;

import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSyncSeeds;
import fluxedCrystals.reference.Messages;
import fluxedCrystals.reference.Names;
import fluxedCrystals.registry.SeedData;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandResetSeeds extends CommandBase
{

	@Override
	public String getCommandName()
	{

		return Names.Commands.RESET_SEEDS;

	}

	@Override
	public int getRequiredPermissionLevel()
	{

		return 4;

	}

	@Override
	public String getCommandUsage(ICommandSender commandSender)
	{

		return Messages.Commands.RESET_SEEDS_USAGE;

	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args)
	{

		SeedRegistry.getInstance().resetSeedRegistry();

		for(int i : SeedRegistry.getInstance().keySet())
		{

			PacketHandler.INSTANCE.sendToAll(new MessageSyncSeeds(SeedRegistry.getInstance().getSeedByID(i)));

		}

	}

}
