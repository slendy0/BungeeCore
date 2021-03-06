package com.slendy.bungeecore.Commands;

import com.slendy.bungeecore.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * ************************************************************************
 * Copyright Slendy (c) 2016. All Right Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of Slendy. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * Thanks
 * ************************************************************************
 */
public class ReplyCommand extends Command{

    public ReplyCommand(){
        super("reply", "", "r" );
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            if (args.length == 0) {
                sender.sendMessage("§3Message §7>> /r <message> - Sends a message to the last person who messaged you");
                return;
            }
            if(!Core.lastmessage.containsKey(sender.getName())){
                sender.sendMessage("§3Message §7>> No one has messaged you recently.");
                return;
            }
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(Core.lastmessage.get(sender.getName()));
            if(target == null){
                sender.sendMessage("§3Message> §a" + Core.lastmessage.get(sender.getName()) + " §7is not online.");
                return;
            }
            String message = "";
            for(int i = 0; i < args.length; i++){
                message = message + args[i] + " ";
            }
            message = ChatColor.translateAlternateColorCodes('&', message.trim());
            if(target.getName().equalsIgnoreCase("Slendy")){
                sender.sendMessage("§dSlendy is often AFK or minimized, due to plugin development.");
                sender.sendMessage("§dPlease be patient if he does not reply instantly.");
            }
            target.sendMessage("§2§l" + sender.getName() + " > " + target.getName() + "§a§l " + message);
            sender.sendMessage("§2§l" + sender.getName() + " > " + target.getName() + "§a§l " + message);
            if(Core.lastmessage.containsKey(sender.getName())){
                Core.lastmessage.remove(sender.getName());
            }
            Core.lastmessage.put(sender.getName(), target.getName());
            if(Core.lastmessage.containsKey(target.getName())){
                Core.lastmessage.remove(target.getName());
            }
            Core.lastmessage.put(target.getName(), sender.getName());
        }


    }
}


