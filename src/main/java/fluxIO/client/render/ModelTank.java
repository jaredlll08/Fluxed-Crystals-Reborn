package fluxIO.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * tank - MrBurd Created using Tabula 4.1.1
 */
public class ModelTank extends ModelBase {
	public ModelRenderer tube;
	public ModelRenderer lid;
	public ModelRenderer lid_1;
	public ModelRenderer tube_1;
	public ModelRenderer tube_2;
	public ModelRenderer valve;
	public ModelRenderer tank;
	public ModelRenderer tank_1;
	public ModelRenderer tank_2;
	public ModelRenderer tank_3;
	public ModelRenderer tank_4;
	public ModelRenderer port;
	public ModelRenderer port_1;
	public ModelRenderer port_2;
	public ModelRenderer port_3;
	public ModelRenderer port_4;
	public ModelRenderer port_5;
	public ModelRenderer port_6;
	public ModelRenderer port_7;
	public ModelRenderer legLF;
	public ModelRenderer legLF_1;
	public ModelRenderer legLF_2;
	public ModelRenderer tank_5;
	public ModelRenderer tank_6;
	public ModelRenderer baseplate;
	public ModelRenderer legLB;
	public ModelRenderer legRB;
	public ModelRenderer legRF;
	public ModelRenderer cube;
	public ModelRenderer port_8;
	public ModelRenderer port_9;
	public ModelRenderer port_10;
	public ModelRenderer port_11;

	public ModelTank() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.tank = new ModelRenderer(this, 0, 0);
		this.tank.setRotationPoint(-7.0F, 15.0F, -7.0F);
		this.tank.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
		this.port_7 = new ModelRenderer(this, 0, 0);
		this.port_7.setRotationPoint(-7.5F, 3.0F, -3.0F);
		this.port_7.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
		this.lid = new ModelRenderer(this, 0, 0);
		this.lid.setRotationPoint(-5.0F, -1.0F, -5.0F);
		this.lid.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
		this.port_1 = new ModelRenderer(this, 0, 0);
		this.port_1.setRotationPoint(-2.0F, 3.0F, -7.5F);
		this.port_1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
		this.tank_1 = new ModelRenderer(this, 0, 0);
		this.tank_1.setRotationPoint(-3.0F, 3.0F, 6.0F);
		this.tank_1.addBox(0.0F, 0.0F, 0.0F, 6, 12, 1, 0.0F);
		this.tank_3 = new ModelRenderer(this, 0, 0);
		this.tank_3.setRotationPoint(-7.0F, 3.0F, -7.0F);
		this.tank_3.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
		this.port_11 = new ModelRenderer(this, 0, 0);
		this.port_11.setRotationPoint(-7.5F, 3.0F, -2.0F);
		this.port_11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.legLF = new ModelRenderer(this, 0, 0);
		this.legLF.setRotationPoint(-6.0F, 20.0F, -6.0F);
		this.legLF.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.port_9 = new ModelRenderer(this, 0, 0);
		this.port_9.setRotationPoint(-3.0F, 3.0F, -7.5F);
		this.port_9.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
		this.tank_4 = new ModelRenderer(this, 0, 0);
		this.tank_4.setRotationPoint(3.0F, 3.0F, -7.0F);
		this.tank_4.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
		this.valve = new ModelRenderer(this, 0, 0);
		this.valve.setRotationPoint(-1.0F, -3.0F, -1.0F);
		this.valve.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
		this.tank_2 = new ModelRenderer(this, 0, 0);
		this.tank_2.setRotationPoint(-7.0F, 0.0F, -7.0F);
		this.tank_2.addBox(0.0F, 0.0F, 0.0F, 14, 3, 14, 0.0F);
		this.legRB = new ModelRenderer(this, 0, 0);
		this.legRB.setRotationPoint(3.0F, 20.0F, 3.0F);
		this.legRB.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.tank_5 = new ModelRenderer(this, 0, 0);
		this.tank_5.setRotationPoint(-7.0F, 3.0F, 3.0F);
		this.tank_5.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
		this.tank_6 = new ModelRenderer(this, 0, 0);
		this.tank_6.setRotationPoint(3.0F, 3.0F, 3.0F);
		this.tank_6.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
		this.port_3 = new ModelRenderer(this, 0, 0);
		this.port_3.setRotationPoint(6.5F, 3.0F, -2.0F);
		this.port_3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.tube = new ModelRenderer(this, 0, 0);
		this.tube.setRotationPoint(-1.0F, -4.0F, -1.0F);
		this.tube.addBox(0.0F, 0.0F, 0.0F, 2, 1, 9, 0.0F);
		this.baseplate = new ModelRenderer(this, 0, 0);
		this.baseplate.setRotationPoint(-7.0F, 23.0F, -7.0F);
		this.baseplate.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
		this.cube = new ModelRenderer(this, 0, 0);
		this.cube.setRotationPoint(-6.0F, 18.0F, -6.0F);
		this.cube.addBox(0.0F, 0.0F, 0.0F, 12, 2, 12, 0.0F);
		this.legLB = new ModelRenderer(this, 0, 0);
		this.legLB.setRotationPoint(-6.0F, 20.0F, 3.0F);
		this.legLB.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.port_6 = new ModelRenderer(this, 0, 0);
		this.port_6.setRotationPoint(6.5F, 3.0F, -3.0F);
		this.port_6.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
		this.legLF_2 = new ModelRenderer(this, 0, 0);
		this.legLF_2.setRotationPoint(-1.5F, 20.0F, -1.5F);
		this.legLF_2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.port_10 = new ModelRenderer(this, 0, 0);
		this.port_10.setRotationPoint(6.5F, 3.0F, 2.0F);
		this.port_10.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
		this.lid_1 = new ModelRenderer(this, 0, 0);
		this.lid_1.setRotationPoint(-3.0F, -2.0F, -3.0F);
		this.lid_1.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6, 0.0F);
		this.port_8 = new ModelRenderer(this, 0, 0);
		this.port_8.setRotationPoint(-2.0F, 14.0F, -7.5F);
		this.port_8.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
		this.port_5 = new ModelRenderer(this, 0, 0);
		this.port_5.setRotationPoint(-7.5F, 14.0F, -2.0F);
		this.port_5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.port_4 = new ModelRenderer(this, 0, 0);
		this.port_4.setRotationPoint(6.5F, 14.0F, -2.0F);
		this.port_4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.port_2 = new ModelRenderer(this, 0, 0);
		this.port_2.setRotationPoint(-7.5F, 3.0F, 2.0F);
		this.port_2.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
		this.legRF = new ModelRenderer(this, 0, 0);
		this.legRF.setRotationPoint(3.0F, 20.0F, -6.0F);
		this.legRF.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.tube_1 = new ModelRenderer(this, 0, 0);
		this.tube_1.setRotationPoint(-1.0F, 19.0F, 6.0F);
		this.tube_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.tube_2 = new ModelRenderer(this, 0, 0);
		this.tube_2.setRotationPoint(-1.0F, -3.0F, 7.0F);
		this.tube_2.addBox(0.0F, 0.0F, 0.0F, 2, 23, 1, 0.0F);
		this.legLF_1 = new ModelRenderer(this, 0, 0);
		this.legLF_1.setRotationPoint(-6.0F, 20.0F, -6.0F);
		this.legLF_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
		this.port = new ModelRenderer(this, 0, 0);
		this.port.setRotationPoint(2.0F, 3.0F, -7.5F);
		this.port.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.tank.render(f5);
		this.port_7.render(f5);
		this.lid.render(f5);
		this.port_1.render(f5);
		this.tank_1.render(f5);
		this.tank_3.render(f5);
		this.port_11.render(f5);
		this.legLF.render(f5);
		this.port_9.render(f5);
		this.tank_4.render(f5);
		this.valve.render(f5);
		this.tank_2.render(f5);
		this.legRB.render(f5);
		this.tank_5.render(f5);
		this.tank_6.render(f5);
		this.port_3.render(f5);
		this.tube.render(f5);
		this.baseplate.render(f5);
		this.cube.render(f5);
		this.legLB.render(f5);
		this.port_6.render(f5);
		this.legLF_2.render(f5);
		this.port_10.render(f5);
		this.lid_1.render(f5);
		this.port_8.render(f5);
		this.port_5.render(f5);
		this.port_4.render(f5);
		this.port_2.render(f5);
		this.legRF.render(f5);
		this.tube_1.render(f5);
		this.tube_2.render(f5);
		this.legLF_1.render(f5);
		this.port.render(f5);
	}
	
	public void render(float f5){
		this.tank.render(f5);
		this.port_7.render(f5);
		this.lid.render(f5);
		this.port_1.render(f5);
		this.tank_1.render(f5);
		this.tank_3.render(f5);
		this.port_11.render(f5);
		this.legLF.render(f5);
		this.port_9.render(f5);
		this.tank_4.render(f5);
		this.valve.render(f5);
		this.tank_2.render(f5);
		this.legRB.render(f5);
		this.tank_5.render(f5);
		this.tank_6.render(f5);
		this.port_3.render(f5);
		this.tube.render(f5);
		this.baseplate.render(f5);
		this.cube.render(f5);
		this.legLB.render(f5);
		this.port_6.render(f5);
		this.legLF_2.render(f5);
		this.port_10.render(f5);
		this.lid_1.render(f5);
		this.port_8.render(f5);
		this.port_5.render(f5);
		this.port_4.render(f5);
		this.port_2.render(f5);
		this.legRF.render(f5);
		this.tube_1.render(f5);
		this.tube_2.render(f5);
		this.legLF_1.render(f5);
		this.port.render(f5);	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}