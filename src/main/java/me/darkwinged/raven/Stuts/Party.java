package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class Party {

    @Getter
    private final UUID partyID;
    @Getter @Setter
    private UUID leader;
    @Getter @Setter
    private List<UUID> members;
    @Getter @Setter
    private List<UUID> invited;
    @Getter @Setter
    private boolean open;

    public boolean isMember(UUID uuid) {
        return members.contains(uuid);
    }

    public boolean isInvited(UUID uuid) {
        return invited.contains(uuid);
    }

    public void addMember(UUID uuid) {
        members.add(uuid);
    }

    public void removeMember(UUID uuid) {
        members.remove(uuid);
    }

    public void addInvite(UUID uuid) {
        invited.add(uuid);
    }

    public void removeInvite(UUID uuid) {
        invited.remove(uuid);
    }

}
