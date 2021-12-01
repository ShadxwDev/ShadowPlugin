package uk.co.tmdavies.shadowplugin;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadowplugin.utils.ShadowLogger;

public abstract class ShadowPlugin extends JavaPlugin {

    private String pluginName;
    private double pluginVersion;

    public static ShadowLogger shadowLogger;

    public ShadowPlugin(String pluginName, double pluginVersion) {

        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;

        startUp();

    }

    @Override
    public void onLoad() {

        this.load();

        shadowLogger = new ShadowLogger();

    }

    @Override
    public void onEnable() {

        this.enable();

    }

    @Override
    public void onDisable() {

        this.disable();

    }

    public abstract void load();

    public abstract void enable();

    public abstract void disable();

    public void startUp() {

        shadowLogger.log(ShadowLogger.Reason.GENERIC, "&8-=*=-=*=-=*=-=*=-=*=-=*=-");
        shadowLogger.log(ShadowLogger.Reason.GENERIC, "&7Enabling &4" + this.pluginName);
        shadowLogger.log(ShadowLogger.Reason.GENERIC, "&7Plugin Version &4" + this.pluginVersion);
        shadowLogger.log(ShadowLogger.Reason.GENERIC, "&8&oloaded with Shadow");
        shadowLogger.log(ShadowLogger.Reason.GENERIC, "&8-=*=-=*=-=*=-=*=-=*=-=*=-");

    }

}
