package net.minecraft.src;

public class BlockHavreCubeOut extends BlockBreakable {
	
	public BlockHavreCubeOut(int i) {
		
		super(i, ModLoader.addOverride("/terrain.png", "assets/havrecubeout.png"), Material.cloth, false);
		
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
