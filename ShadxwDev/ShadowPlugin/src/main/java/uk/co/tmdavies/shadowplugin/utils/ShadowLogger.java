package uk.co.tmdavies.shadowplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShadowLogger {

    private Logger logger;

    public ShadowLogger() {

        init();

    }

    public void init() {

        if (this.logger == null) {

            logger = new Logger("Shadow", null) {};

            logger.setParent(Bukkit.getLogger());
            logger.setLevel(Level.ALL);

        }

    }

    public void log(Reason reason, String message) {

        this.logger.info(reason.getColour() + reason.getPrefix() + ShadowUtils.Chat(message));

    }

    public void error(Reason reason, String message) {

        this.logger.info(Reason.ERROR.getColour() + Reason.ERROR.getPrefix() + reason.getPrefix() + ShadowUtils.Chat(message));

    }

    public enum Reason {

        GENERIC("", ChatColor.WHITE),
        ERROR("[Error] ", ChatColor.RED),
        CONFIG("[Config] ", ChatColor.BLUE),
        SQL("[SQL] ", ChatColor.GOLD);

        private String prefix;
        private ChatColor colour;

        Reason(String prefix, ChatColor colour) {

            this.prefix = prefix;
            this.colour = colour;

        }

        public String getPrefix() {

            return this.prefix;

        }

        public ChatColor getColour() {

            return this.colour;

        }

    }

}