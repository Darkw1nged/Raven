package me.darkwinged.raven.struts.enums;

import lombok.Getter;

public enum Suits {

    SHADOW_SENTINEL_SUIT("Shadow Sentinel Suit", "A sleek and stealthy suit adorned with dark, ethereal patterns, empowering the wearer with enhanced agility and shadow manipulation abilities.", 10, "Common"),
    ARCANE_MAGE_ATTIRE("Arcane Mage Attire", "Robes imbued with arcane energies, granting the wearer enhanced spellcasting abilities and knowledge of ancient magical secrets.", 80, "Common"),
    DRACONIC_KNIGHT_ARMOR("Draconic Knight Armor", "Armor crafted from dragon scales, providing exceptional protection, strength, and resistance to fire.", 100, "Common"),
    NATURES_GUARDIAN_GARB("Nature's Guardian Garb", "Garments infused with nature's essence, granting the wearer enhanced affinity with plants, animals, and the ability to harness nature-based magic.", 120, "Common"),
    CRYSTAL_SHAPER_OUTFIT("Crystal Shaper Outfit", "An outfit adorned with enchanted crystals, granting the wearer the ability to manipulate and shape crystalline structures.", 140, "Common"),
    LUNAR_PRIEST_ROBES("Lunar Priest Robes", "Robes blessed by lunar deities, granting the wearer the ability to harness the power of the moon, cast moonlight-based spells, and enhance their healing abilities.", 160, "Common"),
    ABYSSAL_WARRIOR_PLATE("Abyssal Warrior Plate", "Armor forged in the depths of the abyss, providing the wearer with resistance to dark magic, increased strength, and the ability to tap into abyssal powers.", 180, "Common"),
    CELESTIAL_SORCERER_VESTMENTS("Celestial Sorcerer Vestments", "Vestments adorned with celestial symbols, granting the wearer enhanced spellcasting abilities, celestial protection, and the ability to channel celestial energy.", 200, "Rare"),
    FLAMEBOUND_ASSASSIN_ATTIRE("Flamebound Assassin Attire", "Attire infused with the essence of fire, granting the wearer enhanced agility, stealth, and the ability to conjure and control flames.", 220, "Rare"),
    FROSTBORNE_RANGER_GARMENTS("Frostborne Ranger Garments", "Garments woven with the power of ice, granting the wearer enhanced archery skills, resistance to cold, and the ability to manipulate ice and snow.", 240, "Rare"),
    TIME_WARDEN_ARMOR("Time Warden Armor", "Armor infused with the essence of time, granting the wearer the ability to manipulate time, enhance their reflexes, and cast spells that alter the flow of time.", 260, "Rare"),
    STORMCALLER_ROBES("Stormcaller Robes", "Robes infused with the power of storms, granting the wearer the ability to summon and control lightning, create thunderstorms, and cast powerful storm-based spells.", 280, "Rare"),
    IRONCLAD_VANGUARD_SUIT("Ironclad Vanguard Suit", "A sturdy suit of armor forged with exceptional craftsmanship, providing excellent protection, enhanced durability, and increased strength.", 0, "Legendary"),
    SOUL_WEAVER_ROBES("Soul Weaver Robes", "Robes woven with threads of souls, granting the wearer the ability to manipulate life force, heal wounds, and cast powerful soul-based spells.", 0, "Legendary"),
    SPIRIT_WALKER_ATTIRE("Spirit Walker Attire", "Attire attuned to the spirit realm, granting the wearer the ability to commune with spirits, manipulate spiritual energy, and traverse the ethereal plane.", 0, "Legendary"),
    ENIGMA_MAGUS_VESTMENTS("Enigma Magus Vestments", "Vestments infused with enigmatic magic, granting the wearer enhanced spellcasting abilities, access to forbidden knowledge, and the ability to manipulate arcane mysteries.", 0, "Legendary"),
    RUNEBOUND_BATTLEMAGE_ARMOR("Runebound Battlemage Armor", "Armor inscribed with ancient runes, granting the wearer enhanced spellcasting abilities, protection against magical attacks, and the ability to tap into runic magic.", 0, "Legendary"),
    VOID_KNIGHT_OUTFIT("Void Knight Outfit", "An outfit infused with the essence of the void, granting the wearer the ability to manipulate shadows, create void portals, and harness the power of the void.", 0, "Legendary"),
    SERAPHIC_CLERIC_ROBES("Seraphic Cleric Robes", "Robes blessed by divine entities, granting the wearer the ability to heal wounds, banish dark forces, and channel holy energy.", 0, "Legendary"),
    DREAMWALKER_GARB("Dreamwalker Garb", "Garments woven from the fabric of dreams, granting the wearer the ability to enter the dream realm, manipulate dreams, and cast powerful illusion spells.", 60, "Common"),
    EBONBLADE_ASSASSIN_ATTIRE("Ebonblade Assassin Attire", "Attire forged in shadowy realms, granting the wearer enhanced stealth, agility, and the ability to manipulate darkness.", 80, "Common"),
    VERDANT_SENTINEL_ARMOR("Verdant Sentinel Armor", "Armor infused with the essence of nature's guardians, granting the wearer enhanced strength, resistance to poisons, and the ability to summon nature's allies.", 100, "Common"),
    ASTRAL_CONJURER_ROBES("Astral Conjurer Robes", "Robes attuned to the astral plane, granting the wearer the ability to summon astral entities, manipulate astral energy, and cast powerful astral spells.", 120, "Common"),
    PYROCLASMIC_WARRIOR_PLATE("Pyroclasmic Warrior Plate", "Armor forged in the heart of volcanic eruptions, providing the wearer resistance to fire, enhanced strength, and the ability to conjure and control magma.", 140, "Common"),
    FROSTFIRE_MAGE_ATTIRE("Frostfire Mage Attire", "Attire blending the powers of ice and fire, granting the wearer the ability to cast frost and fire spells simultaneously, and control the elemental forces.", 160, "Common"),
    CHRONOMANCERS_VESTMENTS("Chronomancer's Vestments", "Vestments infused with the essence of time, granting the wearer the ability to manipulate time, perceive events in the future, and cast spells that alter temporal flow.", 180, "Common"),
    THUNDERLORDS_REGALIA("Thunderlord's Regalia", "Regal attire imbued with the power of thunder, granting the wearer the ability to summon thunderstorms, control lightning, and cast powerful thunder-based spells.", 200, "Rare"),
    STEELSHAPERS_ARMOR("Steelshaper's Armor", "Armor crafted by master blacksmiths, providing exceptional protection, durability, and enhanced weapon handling.", 220, "Rare"),
    SHADOWCASTERS_ROBES("Shadowcaster's Robes", "Robes infused with shadow magic, granting the wearer enhanced stealth, the ability to manipulate shadows, and cast powerful shadow-based spells.", 240, "Rare"),
    FLAMEHEART_CONJURER_ATTIRE("Flameheart Conjurer Attire", "Attire channeling the essence of a fiery heart, granting the wearer enhanced fire magic, resistance to heat, and the ability to summon and control fire spirits.", 260, "Rare"),
    STORMGUARD_SENTINEL_ARMOR("Stormguard Sentinel Armor", "Armor blessed by storm deities, providing resistance against lightning, enhanced endurance, and the ability to summon storm allies.", 280, "Rare"),
    CELESTIAL_CRUSADERS_VESTMENTS("Celestial Crusader's Vestments", "Vestments blessed by celestial beings, granting the wearer enhanced spellcasting abilities, divine protection, and the ability to channel celestial energy.", 300, "Rare"),
    ASTRAL_TEMPEST_ROBES("Astral Tempest Robes", "Robes attuned to the chaotic energies of the astral tempest, granting the wearer the ability to harness unpredictable magic, summon tempestuous winds, and manipulate chaotic forces.", 320, "Rare"),
    VOIDBORNE_ASSASSIN_ATTIRE("Voidborne Assassin Attire", "Attire infused with the essence of the void, granting the wearer the ability to blend into shadows, manipulate darkness, and strike with deadly precision.", 0, "Legendary"),
    ESSENCE_CHANNELERS_GARMENTS("Essence Channeler's Garments", "Garments woven with threads of elemental essences, granting the wearer the ability to channel and manipulate various elemental powers.", 0, "Legendary");


    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int price;
    @Getter
    private final String rarity;

    Suits(String name, String description, int price, String rarity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rarity = rarity;
    }


}
