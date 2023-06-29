package me.darkwinged.raven.struts.enums;

import lombok.Getter;
import me.darkwinged.raven.struts.Reward;

public enum Achievements {

    ARCHITECTURAL_EXPLORER("Architectural Explorer", "Uncover the secrets of every majestic architectural masterpiece in the lobby.", null),
    SOCIAL_NETWORKER("Social Networker", "Engage in lively conversations with 100 players, building connections within the lobby.", null),
    FASHION_ICON("Fashion Icon", "Showcase your impeccable sense of style by creating a stunning ensemble with rare and exclusive lobby cosmetic items.", null),
    RELIC_SEEKER("Relic Seeker", "Embark on a quest to discover and collect ancient relics hidden throughout Raven's Realm.", null),
    GRAVITY_DEFIER("Gravity Defier", "Master the art of defying gravity by completing 100 daring and exhilarating leaps.", null),
    VELOCITY_VANGUARD("Velocity Vanguard", "Set a new speed record by completing a lap around the lobby in under 60 seconds.", null),
    MYSTICAL_MASQUERADE("Mystical Masquerade", "Attend and participate in a grand celebration within Raven's Realm, adorned in an elaborate costume of your choice.", null),
    BONDED_COMPANIONS("Bonded Companions", "Forge a bond with a companion by completing a quest together.", null),
    FLAWLESS_CONQUEROR("Flawless Conqueror", "Defeat the final boss of a dungeon without taking any damage.", null),
    SUMMIT_ASCENDANT("Summit Ascendant", "Scale the highest peaks of Raven's Realm and marvel at the breathtaking vistas that unfold before you.", null),
    BEAST_WHISPERER("Beast Whisperer", "Interact with and tame 50 mythical creatures residing within the lobby.", null),
    ARCHITECTURAL_VISIONARY("Architectural Visionary", "Embark on a grand exploration, uncovering the architectural wonders that shape Raven's Realm.", null),
    CHAMPIONS_TRIUMPH("Champion's Triumph", "Overcome 10 demanding challenges unique to Raven's Realm and emerge as a true champion.", null),
    TEAM_GLORY("Team Glory", "Achieve victory as a cohesive unit in a team-based lobby minigame, displaying exceptional teamwork and coordination.", null),
    PYROTECHNIC_VIRTUOSO("Pyrotechnic Virtuoso", "Illuminate the skies of Raven's Realm with 100 mesmerizing displays of fireworks.", null),
    CAVE_EXPLORER("Cave Explorer", "Successfully navigate through the perplexing twists and turns of the lobby's intricate cave system.", null),
    MASTER_ANGLER("Master Angler", "Reel in and capture 50 exotic fish species thriving within Raven's Realm.", null),
    SKYDIVING_MAVERICK("Skydiving Maverick", "Take a leap of faith and plunge into the depths of Raven's Realm, free-falling for 10 seconds.", null),
    MASTER_CRAFTSMAN("Master Craftsman", "Craft 100 unique items using the crafting table located within the lobby.", null),
    LOBBY_LEGEND("Lobby Legend", "Achieve the highest rank in the lobby, becoming a true legend of Raven's Realm.", null);

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final Reward reward;

    Achievements(String name, String description, Reward reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
    }

}
