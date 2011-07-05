package net.minecraft.src;


public class BlockHavreCubeIn extends Block {

	public BlockHavreCubeIn(int i) {
		
		super(i, Material.cloth);
        blockIndexInTexture = mod_HavreCube.havreCubeInTexturehcf;
		
    }

    public int getBlockTextureFromSide(int i) {
        
        if(i == 0)
        	return 2;
        
        if(i == 1)
            return mod_HavreCube.havreCubeInTexturehcf;

        if(i == 2)
        	return mod_HavreCube.havreCubeInTexturehcb;
        
        if(i == 3)
        	return mod_HavreCube.havreCubeInTexturehccg;
        
        if(i == 4)
        	return mod_HavreCube.havreCubeInTexturehccd;
        
        if(i == 5)
        	return mod_HavreCube.havreCubeInTexturehcc;
        
        return 0;//mod_HavreCube.havreCubeInTexturehccd;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        return null;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
        if(world.multiplayerWorld)
        {
            return;
        } else
        {
            entity.setInPortalHavreCube();
            return;
        }
    }
}

    //public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
