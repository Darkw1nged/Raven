package me.darkwinged.raven.Stuts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

public record GiftPack(@Getter UUID uuid, @Getter int amount, @Getter List<Cache> caches) {}
