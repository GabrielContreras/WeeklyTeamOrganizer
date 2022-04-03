package com.mooglecake.enums;

public enum SubRole {
    SHIELD,
    PURE,
    RANGED,
    MELEE,
    CASTER,
    TANK;

    public static SubRole getJobSubRole(Job job){
        if(job == Job.AST || job == Job.SCH || job == Job.WHM || job == Job.SGE){
            if(job == Job.AST || job == Job.WHM)
                return SubRole.PURE;
            else
                return SubRole.SHIELD;
        } else if (job == Job.PLD || job == Job.WAR || job == Job.DRK || job == Job.GNB){
            return SubRole.TANK;
        } else {
            if(job == Job.MNK || job == Job.SAM || job == Job.NIN || job == Job.RPR || job == Job.DRG)
                return SubRole.MELEE;
            else if(job == Job.RDM || job == Job.BLM || job ==Job.SMN)
                return SubRole.CASTER;
            else
                return SubRole.RANGED;
        }
    }
}
