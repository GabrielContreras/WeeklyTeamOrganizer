package com.mooglecake.enums;

public enum Job {
    PLD,
    GNB,
    DRK,
    WAR,
    AST,
    WHM,
    SCH,
    SGE,
    RPR,
    MNK,
    DRG,
    NIN,
    SAM,
    MCH,
    DNC,
    BRD,
    RDM,
    BLM,
    SMN;

    public static Job convertStringToJobEnum(String jobString){
        return switch (jobString.toUpperCase()) {
            case "PLD" -> Job.PLD;
            case "GNB" -> Job.GNB;
            case "DRK" -> Job.DRK;
            case "WAR" -> Job.WAR;
            case "AST" -> Job.AST;
            case "WHM" -> Job.WHM;
            case "SCH" -> Job.SCH;
            case "SGE" -> Job.SGE;
            case "RPR" -> Job.RPR;
            case "MNK" -> Job.MNK;
            case "DRG" -> Job.DRG;
            case "NIN" -> Job.NIN;
            case "SAM" -> Job.SAM;
            case "MCH" -> Job.MCH;
            case "DNC" -> Job.DNC;
            case "BRD" -> Job.BRD;
            case "RDM" -> Job.RDM;
            case "BLM" -> Job.BLM;
            case "SMN" -> Job.SMN;
            default -> null;
        };
    }
}
