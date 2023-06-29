package me.darkwinged.raven.managers;

import lombok.Getter;
import lombok.Setter;
import me.darkwinged.raven.struts.Party;

import java.util.ArrayList;
import java.util.List;

public class PartyManager {

    @Getter @Setter
    private int maxPartySize = 24;
    @Getter @Setter
    private List<Party> parties = new ArrayList<>();

}
