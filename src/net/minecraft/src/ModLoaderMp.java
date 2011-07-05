// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            Packet230ModLoader, ModLoader, BaseMod, BaseModMp, 
//            Packet100OpenWindow, EntityPlayerSP, Container, GuiScreen, 
//            Packet, World, NetClientHandler

public class ModLoaderMp
{

    public static void Init()
    {
        if(!hasInit)
        {
            init();
        }
    }

    public static void HandleAllPackets(Packet230ModLoader packet230modloader)
    {
        if(!hasInit)
        {
            init();
        }
        packet230Received = true;
        if(packet230modloader.modId == "ModLoaderMP".hashCode())
        {
            handleModCheck(packet230modloader);
        } else
        {
            for(int i = 0; i < ModLoader.getLoadedMods().size(); i++)
            {
                BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
                if(!(basemod instanceof BaseModMp))
                {
                    continue;
                }
                BaseModMp basemodmp = (BaseModMp)basemod;
                if(basemodmp.getId() != packet230modloader.modId)
                {
                    continue;
                }
                basemodmp.HandlePacket(packet230modloader);
                break;
            }

        }
    }

    public static Class HandleNetClientHandlerEntities(int i)
    {
        if(!hasInit)
        {
            init();
        }
        if(netClientHandlerEntityMap.containsKey(Integer.valueOf(i)))
        {
            return (Class)netClientHandlerEntityMap.get(Integer.valueOf(i));
        } else
        {
            return null;
        }
    }

    public static void SendPacket(BaseModMp basemodmp, Packet230ModLoader packet230modloader)
    {
        if(!hasInit)
        {
            init();
        }
        if(basemodmp == null)
        {
            IllegalArgumentException illegalargumentexception = new IllegalArgumentException("baseModMp cannot be null.");
            ModLoader.getLogger().throwing("ModLoaderMp", "SendPacket", illegalargumentexception);
            ModLoader.ThrowException("baseModMp cannot be null.", illegalargumentexception);
        } else
        {
            packet230modloader.modId = basemodmp.getId();
            sendPacket(packet230modloader);
        }
    }

    public static void RegisterGUI(BaseModMp basemodmp, int i)
    {
        if(!hasInit)
        {
            init();
        }
        if(guiModMap.containsKey(Integer.valueOf(i)))
        {
            System.out.println("RegisterGUI error: inventoryType already registered.");
        } else
        {
            guiModMap.put(Integer.valueOf(i), basemodmp);
        }
    }

    public static void HandleGUI(Packet100OpenWindow packet100openwindow)
    {
        if(!hasInit)
        {
            init();
        }
        BaseModMp basemodmp = (BaseModMp)guiModMap.get(Integer.valueOf(packet100openwindow.inventoryType));
        GuiScreen guiscreen = basemodmp.HandleGUI(packet100openwindow.inventoryType);
        if(guiscreen != null)
        {
            ModLoader.OpenGUI(ModLoader.getMinecraftInstance().thePlayer, guiscreen);
            ModLoader.getMinecraftInstance().thePlayer.craftingInventory.windowId = packet100openwindow.windowId;
        }
    }

    public static void RegisterNetClientHandlerEntity(Class class1, int i)
    {
        if(!hasInit)
        {
            init();
        }
        if(i > 255)
        {
            System.out.println("RegisterNetClientHandlerEntity error: entityId cannot be greater than 255.");
        } else
        if(netClientHandlerEntityMap.containsKey(Integer.valueOf(i)))
        {
            System.out.println("RegisterNetClientHandlerEntity error: entityId already registered.");
        } else
        {
            if(i > 127)
            {
                i -= 256;
            }
            netClientHandlerEntityMap.put(Integer.valueOf(i), class1);
        }
    }

    public static void SendKey(BaseModMp basemodmp, int i)
    {
        if(!hasInit)
        {
            init();
        }
        if(basemodmp == null)
        {
            IllegalArgumentException illegalargumentexception = new IllegalArgumentException("baseModMp cannot be null.");
            ModLoader.getLogger().throwing("ModLoaderMp", "SendKey", illegalargumentexception);
            ModLoader.ThrowException("baseModMp cannot be null.", illegalargumentexception);
        } else
        {
            Packet230ModLoader packet230modloader = new Packet230ModLoader();
            packet230modloader.modId = "ModLoaderMP".hashCode();
            packet230modloader.packetType = 1;
            packet230modloader.dataInt = (new int[] {
                basemodmp.getId(), i
            });
            sendPacket(packet230modloader);
        }
    }

    private static void init()
    {
        hasInit = true;
        try
        {
            Method method;
            try
            {
                method = (net.minecraft.src.Packet.class).getDeclaredMethod("a", new Class[] {
                    Integer.TYPE, Boolean.TYPE, Boolean.TYPE, java.lang.Class.class
                });
            }
            catch(NoSuchMethodException nosuchmethodexception1)
            {
                method = (net.minecraft.src.Packet.class).getDeclaredMethod("addIdClassMapping", new Class[] {
                    Integer.TYPE, Boolean.TYPE, Boolean.TYPE, java.lang.Class.class
                });
            }
            method.setAccessible(true);
            method.invoke(null, new Object[] {
                Integer.valueOf(230), Boolean.valueOf(true), Boolean.valueOf(true), net.minecraft.src.Packet230ModLoader.class
            });
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            ModLoader.getLogger().throwing("ModLoaderMp", "init", illegalaccessexception);
            ModLoader.ThrowException("An impossible error has occurred!", illegalaccessexception);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            ModLoader.getLogger().throwing("ModLoaderMp", "init", illegalargumentexception);
            ModLoader.ThrowException("An impossible error has occurred!", illegalargumentexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            ModLoader.getLogger().throwing("ModLoaderMp", "init", invocationtargetexception);
            ModLoader.ThrowException("An impossible error has occurred!", invocationtargetexception);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            ModLoader.getLogger().throwing("ModLoaderMp", "init", nosuchmethodexception);
            ModLoader.ThrowException("An impossible error has occurred!", nosuchmethodexception);
        }
        catch(SecurityException securityexception)
        {
            ModLoader.getLogger().throwing("ModLoaderMp", "init", securityexception);
            ModLoader.ThrowException("An impossible error has occurred!", securityexception);
        }
    }

    private static void handleModCheck(Packet230ModLoader packet230modloader)
    {
        Packet230ModLoader packet230modloader1 = new Packet230ModLoader();
        packet230modloader1.modId = "ModLoaderMP".hashCode();
        packet230modloader1.packetType = 0;
        packet230modloader1.dataString = new String[ModLoader.getLoadedMods().size()];
        for(int i = 0; i < ModLoader.getLoadedMods().size(); i++)
        {
            packet230modloader1.dataString[i] = ((BaseMod)ModLoader.getLoadedMods().get(i)).toString();
        }

        sendPacket(packet230modloader1);
    }

    private static void sendPacket(Packet230ModLoader packet230modloader)
    {
        if(packet230Received && ModLoader.getMinecraftInstance().theWorld != null && ModLoader.getMinecraftInstance().theWorld.multiplayerWorld)
        {
            ModLoader.getMinecraftInstance().func_20001_q().addToSendQueue(packet230modloader);
        }
    }

    private ModLoaderMp()
    {
    }

    public static final String NAME = "ModLoaderMP";
    private static boolean hasInit = false;
    private static boolean packet230Received = false;
    private static Map netClientHandlerEntityMap = new HashMap();
    private static Map guiModMap = new HashMap();

}
