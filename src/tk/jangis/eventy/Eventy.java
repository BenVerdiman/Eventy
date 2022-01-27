package tk.jangis.eventy;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Eventy extends JavaPlugin {
    FileConfiguration config = this.getConfig();
    public static Eventy main;
    //Little test
    //String diamond = new ItemStack(Material.DIAMOND).getType().toString();


    @Override
    public void onEnable() {
        main = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.getCommand("rrchest").setExecutor(new CommandRChest());
    }

    @Override
    public void onDisable() {

    }
}
