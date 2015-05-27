package fluxedCrystals.command;

import fluxedCrystals.network.PacketHandler;
import fluxedCrystals.network.message.MessageSyncSeed;
import fluxedCrystals.reference.Messages;
import fluxedCrystals.reference.Names;
import fluxedCrystals.registry.Seed;
import fluxedCrystals.registry.SeedRegistry;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

class CommandSeedFromCurrentItem extends CommandBase
{

	@Override
	public String getCommandName () {
		return Names.Commands.SEED_FROM_CURRENT;
	}

	@Override
	public int getRequiredPermissionLevel () {
		return 2;
	}

	@Override
	public String getCommandUsage (ICommandSender commandSender) {

		return Messages.Commands.SEED_FROM_CURRENT_USAGE;

	}

	@Override
	public void processCommand (ICommandSender commandSender, String[] args) {

		if (args.length < 1) {

			throw new WrongUsageException(Messages.Commands.SEED_FROM_CURRENT_USAGE);

		}
		else {

			ItemStack itemStack = ((EntityPlayer) commandSender).getCurrentEquippedItem().copy();

			//noinspection ConstantConditions
			if (itemStack != null) {

				Seed seed = SeedRegistry.getInstance().addTemplateSeed(itemStack);

				if (seed != null) {

					SeedRegistry.getInstance().Save();

					PacketHandler.INSTANCE.sendToAll(new MessageSyncSeed(seed));

					func_152373_a(commandSender, this, Messages.Commands.SEED_FROM_CURRENT_SUCCESS, itemStack.func_151000_E());

				}

			}
			else {

				throw new WrongUsageException(Messages.Commands.NO_ITEM);

			}

		}

	}

	@Override
	public List addTabCompletionOptions (ICommandSender commandSender, String[] args) {

		return null;

	}

}
