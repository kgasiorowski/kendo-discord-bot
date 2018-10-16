package eventhandlers;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 *
 */
public class ReadyEventListener extends ListenerAdapter {

    /**
     * Triggers when the bot is about to run.
     *
     * @param event
     */
    @Override
    public void onReady(ReadyEvent event){

        System.out.println("The bot is ready!");

    }

}