package tk.jangis.eventy;

import static tk.jangis.eventy.Eventy.main;

public class ReloadConfig {
    public void reloadConfig(){
        main.getConfig().options().copyDefaults(true);
        main.saveDefaultConfig();
    }
}
