package me.darkwinged.raven.struts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Guild {

    @Getter
    private final UUID guildID;
    @Getter @Setter
    private UUID ownerID;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String prefix;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private boolean isPublic;
    @Getter @Setter
    private LocalDateTime dateCreated;
    @Getter @Setter
    private List<UUID> members;
    @Getter @Setter
    private List<UUID> invitedMembers;
    @Getter @Setter
    private List<UUID> bannedMembers;

    public boolean isMember(UUID uuid) {
        return members.contains(uuid);
    }

    public void addMember(UUID uuid) {
        members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        members.remove(uuid);
    }

    public boolean isInvited(UUID uuid) {
        return invitedMembers.contains(uuid);
    }

    public void addInvited(UUID uuid) {
        invitedMembers.add(uuid);
    }

    public void removeInvited(UUID uuid) {
        invitedMembers.remove(uuid);
    }

    public boolean isBanned(UUID uuid) {
        return bannedMembers.contains(uuid);
    }

    public void addBanned(UUID uuid) {
        bannedMembers.add(uuid);
    }

    public void removeBanned(UUID uuid) {
        bannedMembers.remove(uuid);
    }

    public boolean isOwner(UUID uuid) {
        return ownerID.equals(uuid);
    }

}
