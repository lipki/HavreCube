package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WorldProvider, WorldChunkManagerNamek, BiomeGenBase, MathHelper, 
//            Vec3D, ChunkProviderNamek, World, Block, 
//            IChunkProvider

public class WorldProviderHavreCube extends WorldProvider
{

    public WorldProviderHavreCube()
    {
    }

    public void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHavreCube(BiomeGenBase.havrecube, 1.0D, 0.0D);
        isNether = false;
        isHavreCubeWorld = true;
        field_6478_e = false;
        worldType = -2;
        generateLightBrightnessTable();
        colorsSunriseSunset = new float[10];
    }

    protected void generateLightBrightnessTable()
    {
        float f = 0.05F;
        for(int i = 0; i <= 15; i++)
        {
            float f1 = 1.0F - (float)i / 15F;
            lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }

    }

    public float calculateCelestialAngle(long l, float f) {
        int i = (int)(l % 24000L);
        float f1 = ((float)i + f) / 24000F - 0.25F;
        if(f1 < 0.0F)
        {
            f1++;
        }
        if(f1 > 1.0F)
        {
            f1--;
        }
        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * 3.1415926535897931D) + 1.0D) / 2D);
        f1 = f2 + (f1 - f2) / 3F;
        return f1;
    }

    public float[] calcSunriseSunsetColors(float f, float f1)
    {
        float f2 = 0.4F;
        float f3 = MathHelper.cos(f * 3.141593F * 2.0F) - 0.0F;
        float f4 = -0F;
        if(f3 >= f4 - f2 && f3 <= f4 + f2)
        {
            float f5 = ((f3 - f4) / f2) * 0.5F + 0.5F;
            float f6 = 3.2F - (1.0F - MathHelper.sin(f5 * 3.141593F)) * 0.99F;
            f6 *= f6;
            colorsSunriseSunset[0] = f5 * 0.3F + 0.7F;
            colorsSunriseSunset[1] = f5 * f5 * 9.7F + 0.2F;
            colorsSunriseSunset[2] = f5 * f5 * 9F + 0.2F;
            colorsSunriseSunset[3] = f6;
            
            return colorsSunriseSunset;
        } else
        {
            return null;
        }
    }

    public Vec3D func_4096_a(float f, float f1)
    {
        int i = 0x8080a0;
        float f2 = MathHelper.cos(f * 3.141593F * 2.0F) * 2.0F + 0.5F;
        if(f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if(f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        float f3 = (float)(i >> 16 & 0xff) / 255F;
        float f4 = (float)(i >> 8 & 0xff) / 255F;
        float f5 = (float)(i & 0xff) / 255F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return Vec3D.createVector(f3, f4, f5);
    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderHavreCube(worldObj, worldObj.getRandomSeed());
    }

    public boolean canCoordinateBeSpawn(int i, int j)
    {
        int k = worldObj.getFirstUncoveredBlock(i, j);
        if(k == Block.bedrock.blockID)
        {
            return false;
        }
        if(k == 0)
        {
            return false;
        } else
        {
            return Block.opaqueCubeLookup[k];
        }
    }

    public boolean func_28112_c()
    {
        return false;
    }

    public float getCloudHeight()
    {
        return 8F;
    }

    private float colorsSunriseSunset[];
}
