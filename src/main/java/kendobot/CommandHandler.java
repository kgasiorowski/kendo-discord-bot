package kendobot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import static Utils.Const.BOT_PREFIX;

/**
 * Handles the actual implementation of commands.
 */
public class CommandHandler {

    public static void handleClear(MessageReceivedEvent event){

        TextChannel channel = event.getTextChannel();

        for(Message m : channel.getIterableHistory())
            if(m.getAuthor() == event.getJDA().getSelfUser())
                m.delete().complete();

    }

    public static void handleEcho(MessageReceivedEvent event){

        String messageContents = event.getMessage().getContentDisplay().substring(BOT_PREFIX.length() + Command.ECHO.name().length());
        TextChannel channel = event.getTextChannel();

        channel.sendMessage(messageContents).queue();

    }

    public static void handleHelp(MessageReceivedEvent event){

        String helpMsg = "This is just a placeholder help message. Soon I'll think of things to put here instead.";
        TextChannel channel = event.getTextChannel();
        channel.sendMessage(helpMsg).queue();

    }

    public static void handleMalformedCommand(MessageReceivedEvent event){

        //Squash this for now.

    }

}
