package eventhandlers;

import kendobot.Command;
import kendobot.CommandHandler;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static Utils.Const.BOT_PREFIX;
import static Utils.Const.TEST_CHANNEL_ID;

/**
 * Most of the code for this bot will reside in here, probably.
 */
public class MessageListener extends ListenerAdapter{

    /**
     * Triggers when a message is sent to the server
     *
     * @param event
     *      has all the information about the even we need, who what where when etc
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        //Ignore this bot's own messages and PM's for now
        if(event.getAuthor() == event.getJDA().getSelfUser() || event.getChannelType() == ChannelType.PRIVATE)
            return;

        //Checks if the message is valid
        Message message = event.getMessage();
        if(message.getContentDisplay().length() < BOT_PREFIX.length()){

            System.out.println("ERROR! An invalid message was passed in.");
            return;

        }

        //Echo every message to the console
        System.out.println("#" + event.getChannel().getName() + " -> " + event.getMember().getEffectiveName() + " : " + message.getContentDisplay());

        //Ignore messages that dont start with the prefix, or arent in the test channel
        if(event.getChannel() == event.getGuild().getTextChannelById(TEST_CHANNEL_ID) && event.getMessage().getContentDisplay().startsWith(BOT_PREFIX)){

            //Cut out the prefix, convert to lower, and cut it up between spaces
            String parsedMsg = message.getContentDisplay().substring(BOT_PREFIX.length()).toLowerCase();
            String[] args = parsedMsg.split(" ");
            String commandAsString = args[0];

            //The command chosen will be stored here
            //The program expects the command to be exactly the same as the
            //enum counterpart in lower case, so HELP should be help, etc
            Command CMD = null;
            for(Command COMMAND : Command.values()){

                if(COMMAND.name().toLowerCase().compareTo(commandAsString) == 0){

                    CMD = COMMAND;
                    break;

                }

            }

            //If the command couldn't be found
            if(CMD == null) {

                //Displays how to write get help from the bot
                CommandHandler.handleMalformedCommand(event);
                return;

            }

            //Main command switch here
            switch(CMD){

                case CLEAR:
                    CommandHandler.handleClear(event);
                    break;
                case ECHO:
                    CommandHandler.handleEcho(event);
                    break;
                case HELP:
                    CommandHandler.handleHelp(event);
                    break;

            }

        }

    }

}
