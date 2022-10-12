package me.moontimer.cratesystem.create.rarity;

public class RarityManager {

    public static Rarity getRarityByCommonName(String commonName) {

        for (Rarity rarity : Rarity.values()) {
            if (rarity.getCommonName().equals(commonName)) {
                return rarity;
            }
        }

        return null;
    }
}
