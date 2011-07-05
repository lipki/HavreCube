package net.minecraft.src;


import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, MapGenCaves, NoiseGeneratorOctaves, Block, 
//            mod_Namek, BiomeGenBase, Chunk, World, 
//            WorldChunkManager, MapGenBase, BlockSand, WorldGenNamekTrees, 
//            WorldGenNamekMinable, WorldGenNamekFlowers, BlockNamekSand, IProgressUpdate

public class ChunkProviderHavreCube
    implements IChunkProvider
{

    public ChunkProviderHavreCube(World world, long l)
    {
        hellRNG = new Random(l);
        worldObj = world;
    }

    public void func_4058_b(int i, int j, byte abyte0[]) 
    {
    	if(i == 0 && j == 0) {
	        for(int k = 0; k < 5; k++) {
	            for(int l = 0; l < 5; l++) {
	                for(int k1 = 127; k1 >= 0; k1--) {
	                    int l1 = (l * 16 + k) * 128 + k1;
	                    if(k1 == 62) {
	                    	if(k == 2 & l == 2)
	                    		 abyte0[l1] = (byte)Block.grass.blockID;
	                        continue;
	                    }
	                    if(k1 == 63) {
	                    	if(k == 2 & l == 2)
	                    		 abyte0[l1] = (byte)mod_HavreCube.havreCubeOut.blockID;
	                    	else abyte0[l1] = (byte)Block.grass.blockID;
	                        continue;
	                    }
	                    if(k1 == 64) {
	                    	if( k == 0 || k == 4 || l == 0 || l == 4 )
	                    		abyte0[l1] = (byte)Block.fence.blockID;
	                    	else if( (k == 1 || k == 3 ) & (l == 1 || l == 3))
	                    		abyte0[l1] = (byte)Block.torchWood.blockID;
	                        continue;
	                    }
	                }
	            }
	        }
    	}

    }

    public Chunk func_538_d(int i, int j)
    {
        return provideChunk(i, j);
    }

    public Chunk provideChunk(int i, int j)
    {
        hellRNG.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        byte abyte0[] = new byte[32768];
        func_4058_b(i, j, abyte0);
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        return chunk;
    }

    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean func_532_a()
    {
        return false;
    }

    public boolean func_536_b()
    {
        return true;
    }

    public String makeString()
    {
        return "HavreCubeInitialPlateformeSource";
    }

    private World worldObj;
    private Random hellRNG;
    private MapGenBase field_4159_s;
    
	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		// TODO Auto-generated method stub
	}
}
