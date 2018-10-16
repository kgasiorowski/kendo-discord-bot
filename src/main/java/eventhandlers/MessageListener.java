package eventhandlers;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import static Utils.Const.BOT_PREFIX;
import static Utils.Const.TEST_CHANNEL_ID;

/**
 *
 */
public class MessageListener extends ListenerAdapter{

    /**
     * Triggers when a message is sent to the server
     *
     * @param event
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        if(event.getAuthor() == event.getJDA().getSelfUser())
            return;

        //Variables
        //Message object
        Message message = event.getMessage();

        if(message.getContentDisplay().length() < BOT_PREFIX.length()){

            System.out.println("ERROR! An invalid message was passed in.");
            return;

        }

        //Message contents
        String messageContents = message.getContentDisplay();
        String messageContentsNoPrefix = messageContents.substring(BOT_PREFIX.length());

        //Channel in which it was sent
        TextChannel channel = event.getTextChannel();

        //User that sent this message
        User author = message.getAuthor();

        //Nickname of the user that sent this message
        String userNickname = message.getMember().getEffectiveName();

        //Nickname of the user as a mention
        String userMention = message.getMember().getAsMention();

        //Test channel (defined in Const.java)
        TextChannel testChannel = event.getGuild().getTextChannelById(TEST_CHANNEL_ID);
        if(testChannel == null){

            System.out.println("Error: no test channel could be found");
            return;

        }

        //Echo every message to the console
        System.out.println("#" + channel.getName() + " -> " + author.getName() + " : " + messageContents);

        if(channel == testChannel && messageContents.startsWith(BOT_PREFIX)){

            if(messageContentsNoPrefix.compareTo("pm") == 0){

                PrivateChannel pmChannel = author.openPrivateChannel().complete();
                pmChannel.sendMessageFormat("Hello %s! You asked for a pm.", userNickname).complete();

                return;

            }

            if(messageContentsNoPrefix.compareTo("clear") == 0){

                for(Message m : channel.getIterableHistory())
                    if(m.getAuthor() == event.getJDA().getSelfUser())
                        m.delete().complete();

                return;

            }

        }

    }

}
