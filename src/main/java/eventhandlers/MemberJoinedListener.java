package eventhandlers;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import static Utils.Const.KENDOKA_ROLE_ID;
import static Utils.Const.TEST_CHANNEL_ID;

public class MemberJoinedListener extends ListenerAdapter {

    /**
     * Triggers when a user joins the server
     *
     * Sends them a greeting and assigns them the "kendoka" role.
     *
     * @param event
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Member member = event.getMember();
        TextChannel welcomeChannel = event.getGuild().getTextChannelById(TEST_CHANNEL_ID);

        System.out.printf("A user joined: %s", member.getEffectiveName());

        welcomeChannel.sendMessageFormat("Welcome to the server, %s! Please set your nickname so we know who you are.", member.getAsMention()).complete();

        GuildController gc = new GuildController(event.getGuild());
        gc.addSingleRoleToMember(member, event.getGuild().getRoleById(KENDOKA_ROLE_ID)).complete();

    }
}