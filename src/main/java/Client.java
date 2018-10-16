import eventhandlers.MessageReceivedHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Channel;

import javax.security.auth.login.LoginException;

public class Client {

    private final static String TOKEN = Token.TOKEN;
    private final static Channel TEST_CHANNEL = null;

    public static void main(String[] args) throws InterruptedException, LoginException{

        JDA jda = new JDABuilder(TOKEN)
                    .addEventListener(new MessageReceivedHandler())
                    .build();

        jda.awaitReady();
        System.out.println("Success!");

    }

}
