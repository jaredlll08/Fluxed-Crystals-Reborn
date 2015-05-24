package fluxedCrystals.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * MultiBlock test - Jared
 * Created using Tabula 4.0.2
 */
public class MultiBlocktest extends ModelBase {
    public ModelRenderer shape1;

    public MultiBlocktest() {
        this.textureWidth = 48;
        this.textureHeight = 48;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-24.0F, -24.0F, -24.0F, 48, 48, 48);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
    }
    
    public void render(float f5){
    	this.shape1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
