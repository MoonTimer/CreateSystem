package me.moontimer.cratesystem.create.rarity;

public enum Rarity {

    Common("§7Gewöhnlich", "common"),
    Uncommon("§aUngewöhnlich", "uncommon"),
    Rare("§eSelten", "rare"),
    Epic("§3Episch", "epic"),
    Mystic("§5Mystisch", "mystic"),
    Legendary("§6§lLegendär", "legendary"),
    Exclusive("§b§lExklusiv", "exclusive"),
    Limited("§bLimie§atiert", "limited"),
    MainWin("§4Hauptgewin", "mainWin"),
    MegaMainWin("§4§lMega §4Hauptgewin", "megaMainWin");

    private final String displayName, commonName;
    Rarity(String displayName, String commonName) {
        this.displayName = displayName;
        this.commonName = commonName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCommonName() {
        return commonName;
    }
}
