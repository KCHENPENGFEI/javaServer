package com.example.card.Data;

public class ParseCryptoKitty {

    private String soldier;

    public ParseCryptoKitty(String symbol, String gene) {
        assert (symbol.equals("KCARD"));
        String first = (String) gene.subSequence(2, 4);
        if (first.compareTo("a") >= 0 && first.compareTo("f") <= 0) {
            // 小于
            soldier = "cavalry";
        }
        else if (first.compareTo("g") >= 0 && first.compareTo("l") <= 0) {
            soldier = "spearman";
        }
        else if (first.compareTo("m") >= 0 && first.compareTo("s") <= 0) {
            soldier = "shieldman";
        }
        else if (first.compareTo("t") >= 0 && first.compareTo("z") <= 0) {
            soldier = "archer";
        }
        else {
            soldier = "infancy";
        }
    }

    public String getSoldier() {
        return soldier;
    }
}
