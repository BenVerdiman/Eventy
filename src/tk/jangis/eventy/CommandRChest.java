package tk.jangis.eventy;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandRChest implements CommandExecutor {
    Eventy main = Eventy.main;
    int minX = main.getConfig().getInt("rChestMinX");
    int maxX = main.getConfig().getInt("rChestMaxX");
    int minY = main.getConfig().getInt("rChestMinY");
    int maxY = main.getConfig().getInt("rChestMaxY");
    int minZ = main.getConfig().getInt("rChestMinZ");
    int maxZ = main.getConfig().getInt("rChestMaxZ");
    int x;
    int y;
    int z;
    int numberOfBlocks = 0;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player) {
            int i=1;
            Player player = (Player) commandSender;
            if(args.length < 1) {
                RandomGenerator random = new RandomGenerator();
                x = random.randomInt(minX, maxX);
                y = random.randomInt(minY, maxY);
                z = random.randomInt(minZ, maxZ);
                Location chestLoc = new Location(player.getWorld(), x, y, z);
                chestLoc.getBlock().setType(Material.CHEST);
                Chest chest = (Chest) chestLoc.getBlock().getState();
                Inventory chestinv = chest.getInventory();
                while (i<=main.getConfig().getInt("rChestNumber")){
                    try {
                        String blockNumber = String.valueOf(i);
                        int quantity = main.getConfig().getInt("quantityB"+blockNumber);
                        ItemStack item2 = new ItemStack(Material.getMaterial(main.config.getString("b"+blockNumber)),quantity);
                        chestinv.addItem(item2);
                        i++;
                    }catch (IllegalArgumentException e){
                        return true;
                    }

                }

                player.sendMessage("Chest spawned at x: " + x + " y: " + y + " z: " + z);
            }else if(args[0].equals("add")){
               // player.sendMessage(String.valueOf(args.length-1));
               String blockNumber;

                while (i<=args.length) {
                    try {
                            blockNumber = String.valueOf(i);
                            main.getConfig().addDefault("b" + blockNumber, args[i]);
                            i++;
                            numberOfBlocks = Integer.parseInt(blockNumber);
                            main.getConfig().addDefault("rChestNumber", numberOfBlocks);
                            main.getConfig().options().copyDefaults(true);
                            main.saveConfig();
                        } catch (ArrayIndexOutOfBoundsException e) {
                            return true;
                        }


                }

            }else if(args[0].equals("reload")){
                ReloadConfig reloadConfig = new ReloadConfig();
                reloadConfig.reloadConfig();
            }else if(args[0].equals("setq")){
                try {
                    main.getConfig().addDefault("quantityB" + args[1], Integer.parseInt(args[2]));
                    main.getConfig().options().copyDefaults(true);
                    main.saveConfig();
                }catch (ArrayIndexOutOfBoundsException e){
                    if(args.length-1 < 1){
                        player.sendMessage(ChatColor.BLUE + "Usage /rchest <blockNumber> <quantity>");
                    }else {
                        return true;
                    }
                }

            }
        }

        return true;
    }
}
