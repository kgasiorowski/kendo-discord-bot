package eventhandlers;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import static Utils.Const.BOT_PREFIX;
import static Utils.Const.TEST_CHANNEL_ID;

public class MessageListener extends ListenerAdapter{

    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        Message message = event.getMessage();
        String messageContents = message.getContentDisplay();

        TextChannel channel = event.getTextChannel();
        User author = message.getAuthor();

        System.out.println("#" + channel.getName() + "->" + author.getName() + " : " + messageContents);

        if(channel.getId().compareTo(TEST_CHANNEL_ID) == 0 &&
                messageContents.startsWith(BOT_PREFIX)){

            System.out.print("Message detected in test channel: ");
            System.out.println(messageContents);




        }


    }


}
