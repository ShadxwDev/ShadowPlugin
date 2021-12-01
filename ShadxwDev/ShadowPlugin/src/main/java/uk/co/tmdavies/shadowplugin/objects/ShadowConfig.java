package uk.co.tmdavies.shadowplugin.objects;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.co.tmdavies.shadowplugin.ShadowPlugin;
import uk.co.tmdavies.shadowplugin.utils.ShadowLogger;

import java.io.File;
import java.io.IOException;

public class ShadowConfig {

    private String pluginName;
    private String configName;
    private File configFile;
    private YamlConfiguration config;

    public ShadowConfig(String pluginName, String configName) {

        this.pluginName = pluginName;
        this.configName = configName;

        if (!this.configName.endsWith(".yml")) this.configName = this.configName + ".yml";

        init();

    }

    private void init() {

        this.configFile = new File("./plugin/" + this.pluginName + "/" + this.configName);

        if (!this.configFile.getParentFile().exists()) this.configFile.getParentFile().mkdirs();

        if (!this.configFile.exists()) {

            try {

                this.configFile.createNewFile();

                ShadowPlugin.shadowLogger.log(ShadowLogger.Reason.CONFIG, "Successfully created " + this.configName);

                this.config = YamlConfiguration.loadConfiguration(this.configFile);

                return;

            } catch (IOException e) {

                ShadowPlugin.shadowLogger.error(ShadowLogger.Reason.CONFIG, "Error creating " + this.configName + ". ShadowConfig#init() Line "
                        + Thread.currentThread().getStackTrace()[1].getLineNumber());

                e.printStackTrace();

            }

        }

        this.config = YamlConfiguration.loadConfiguration(this.configFile);

    }

    public Object get(String path) {

        return this.config.get(path);

    }

    public void set(String path, Object value) {

        this.config.set(path, value);

    }

    public void saveConfig() {

        try {

            config.save(configFile);
            reloadConfig();

        } catch (IOException e) {

            ShadowPlugin.shadowLogger.error(ShadowLogger.Reason.CONFIG, "Error saving " + this.configName + ". ShadowConfig#reloadConfig() Line "
                    + Thread.currentThread().getStackTrace()[1].getLineNumber());

            e.printStackTrace();

        }

    }

    public void reloadConfig() {

        try {

            config.load(configFile);

        } catch (IOException | InvalidConfigurationException e) {

            ShadowPlugin.shadowLogger.error(ShadowLogger.Reason.CONFIG, "Error reloading " + this.configName + ". ShadowConfig#reloadConfig() Line "
                    + Thread.currentThread().getStackTrace()[1].getLineNumber());

            e.printStackTrace();

        }

    }

    public YamlConfiguration getYamlConfig() {

        return this.config;

    }

}
