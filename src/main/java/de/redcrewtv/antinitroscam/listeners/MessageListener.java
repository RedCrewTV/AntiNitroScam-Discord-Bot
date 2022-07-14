package de.redcrewtv.antinitroscam.listeners;

import de.redcrewtv.antinitroscam.AntiNitroScam;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;

/**
 * This file is a JavaDoc!
 * Created: 7/14/2022
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        Message msg = e.getMessage();
        for (String domain : AntiNitroScam.getDomains()){
            if(msg.getContentRaw().contains(domain)){
                msg.delete().queue();
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("AntiNitroScam | Log");
                eb.setColor(new Color(236, 112, 99));

                eb.setDescription("Nitro scam detected!\n\n**Name:** `" + e.getAuthor().getName() + "`\n**ID:** `" + e.getAuthor().getId() + "`\n**Content:**\n`" + msg.getContentRaw() + "`");
                eb.setFooter("Made by https://redcrewtv/", "https://yt3.ggpht.com/ytc/AKedOLQfjhNw6j8aUN_QZ8IjF7wka0vK45YT4fp9rBOGyA=s88-c-k-c0x00ffffff-no-rj");

                AntiNitroScam.getJda().getTextChannelById(AntiNitroScam.getProperties().getProperty("channel.log.id")).sendMessage(" ").setEmbeds(eb.build()).queue();
                return;
            }
        }
    }

}
