package fluxIO.network;

import fluxIO.tileEntity.fluids.TileEntityFluidTank;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageFluid implements IMessage, IMessageHandler<MessageFluid, IMessage> {
	public int fluid;
	public int amount;
	public int x, y, z;

	public MessageFluid() {
	}

	public MessageFluid(TileEntityFluidTank tile) {
		this.fluid = 0;
		if (tile.tank.getFluid() != null)
			this.fluid = tile.tank.getFluid().getFluidID();
		this.amount = tile.tank.getFluidAmount();
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
	}

	public MessageFluid(int fluid, int amount, int x, int y, int z) {
		this.fluid = fluid;
		this.amount = amount;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.fluid = buf.readInt();
		this.amount = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(fluid);
		buf.writeInt(amount);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

	}

	@Override
	public IMessage onMessage(MessageFluid message, MessageContext ctx) {

		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

		if (tileEntity instanceof TileEntityFluidTank) {
			Fluid fluid = null;
			FluidStack stack = null;
			if (message.fluid != 0) {
				fluid = FluidRegistry.getFluid(message.fluid);
				stack = new FluidStack(fluid, message.amount);
			}
			((TileEntityFluidTank) tileEntity).tank.setFluid(stack);

		}

		return null;
	}
}