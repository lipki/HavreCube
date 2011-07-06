package net.minecraft.src;


public class mod_HavreCube extends BaseMod {
	
	@Override
	public String Version() { return "HavreCube 1.6.1"; }

    public static int havreCubeInTexturehcf;
    public static int havreCubeInTexturehcb;
    public static int havreCubeInTexturehcc;
    public static int havreCubeInTexturehccd;
    public static int havreCubeInTexturehccg;
    
	public static Block havreCubeIn;
	public static Block havreCubeOut;
	
	public static double surfaceX;
	public static double surfaceZ;
	public static long Time;
	
	public mod_HavreCube() {
		
		havreCubeInTexturehcf = ModLoader.addOverride("/terrain.png", "assets/hcf.png");
		havreCubeInTexturehcb = ModLoader.addOverride("/terrain.png", "assets/hcb.png");
		havreCubeInTexturehcc = ModLoader.addOverride("/terrain.png", "assets/hcc.png");
		havreCubeInTexturehccd = ModLoader.addOverride("/terrain.png", "assets/hccd.png");
		havreCubeInTexturehccg = ModLoader.addOverride("/terrain.png", "assets/hccg.png");
		
		havreCubeIn = new BlockHavreCubeIn(100).setBlockName("HavreCubeIn");
		havreCubeOut = new BlockHavreCubeOut(101).setBlockName("HavreCubeOut").setLightValue(1.0F);
		
		ModLoader.RegisterBlock(havreCubeIn);
		ModLoader.RegisterBlock(havreCubeOut);

		ModLoader.AddRecipe(new ItemStack(havreCubeIn,1), new Object[] {
			"LLL", "LSL", "LLL", 
			Character.valueOf('S'), Item.seeds,
			Character.valueOf('L'), Item.leather
		});
		
		ModLoader.AddRecipe(new ItemStack(Item.pocketSundial,1), new Object[] {
			"S ", "  ", 
			Character.valueOf('S'), Block.dirt
		});

		ModLoader.AddRecipe(new ItemStack(havreCubeOut,1), new Object[] {
			"  ", "  ", 
			Character.valueOf('S'), Block.netherrack
		});
	}
}
