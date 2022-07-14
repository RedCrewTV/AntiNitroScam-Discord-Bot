package de.redcrewtv.antinitroscam;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.redcrewtv.antinitroscam.listeners.MessageListener;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.io.FileUtils;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * This file is a JavaDoc!
 * Created: 7/14/2022
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */

public class AntiNitroScam {

    @Getter
    private static JDA jda;
    @Getter
    private static Properties properties;
    @Getter
    private static final List<String> domains = new ArrayList<>();

    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        domains.addAll((Collection<? extends String>) new ObjectMapper().readValue(AntiNitroScam.class.getClassLoader().getResource("domain-list.json"), Map.class).get("domains"));
        try(InputStream input = AntiNitroScam.class.getClassLoader().getResourceAsStream("config.properties")){
            if(input == null){
                System.out.println("Unable to find config.properties");
                return;
            }
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) { e.printStackTrace(); }
        jda = JDABuilder.createDefault(getProperties().getProperty("token"))
                .setActivity(Activity.playing("https://redcrewtv.de/"))
                .addEventListeners(new MessageListener())
                .build().awaitReady();
    }

}
