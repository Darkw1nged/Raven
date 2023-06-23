package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class Friend {

    @Getter
    private final UUID uuid;
    @Getter
    private final UUID friend;
    @Getter @Setter
    private boolean bestFriend;
    @Getter @Setter
    private String nickname;
    @Getter
    private final LocalDateTime friendSince;

}
