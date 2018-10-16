import Utils.Token;
import eventhandlers.MemberJoinedListener;
import eventhandlers.MessageListener;
import eventhandlers.ReadyEventListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Client {

    private final static String TOKEN = Token.TOKEN;

    public static void main(String[] args) throws InterruptedException, LoginException{

        JDA jda = new JDABuilder(TOKEN)
                    .addEventListener(new MessageListener())
                    .addEventListener(new ReadyEventListener())
                    .addEventListener(new MemberJoinedListener())
                    .build();

        jda.awaitReady();

    }

}
