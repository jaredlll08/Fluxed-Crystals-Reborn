package fluxedCrystals.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerProxy extends CommonProxy
{


	@Override
	public ClientProxy getClientProxy()
	{

		return null;

	}

	@Override
	public World getClientWorld()
	{

		return null;

	}

	@Override
	public void registerRenderers()
	{
		
	}

	@Override
	public boolean isServer()
	{

		return true;

	}

	@Override
	public boolean isClient()
	{

		return false;

	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

}
