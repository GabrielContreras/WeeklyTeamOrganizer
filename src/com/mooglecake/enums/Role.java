package com.mooglecake.enums;

public enum Role {
        TANK,
        HEALER,
        DPS;

        public static Role getJobRole(Job job){
                if(job == Job.AST || job == Job.SCH || job == Job.WHM || job == Job.SGE){
                        return Role.HEALER;
                } else if (job == Job.PLD || job == Job.WAR || job == Job.DRK || job == Job.GNB){
                        return Role.TANK;
                } else {
                        return Role.DPS;
                }
        }
}

