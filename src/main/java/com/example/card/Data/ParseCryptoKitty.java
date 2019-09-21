package com.example.card.Data;

public class ParseCryptoKitty {

    private String soldier;

    public ParseCryptoKitty(String symbol, String gene) {
        assert (symbol.equals("KCARD"));
        String first = (String) gene.subSequence(0, 1);
        if (first.compareTo("a") >= 0 && first.compareTo("e") <= 0) {
            // 小于
            soldier = "infancy";
        }
        else if (first.compareTo("f") >= 0 && first.compareTo("j") <= 0) {
            soldier = "spearman";
        }
        else if (first.compareTo("k") >= 0 && first.compareTo("p") <= 0) {
            soldier = "shieldman";
        }
        else if (first.compareTo("q") >= 0 && first.compareTo("v") <= 0) {
            soldier = "archer";
        }
        else {
            soldier = "cavalry";
        }
    }

    public String getSoldier() {
        return soldier;
    }
}
