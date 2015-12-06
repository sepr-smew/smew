package com.sepr.smew.utils;

import com.badlogic.gdx.physics.box2d.World;

public enum WorldManager {;
    private static World world;
    public static void setWorld(World world) { WorldManager.world = world; }
    public static World getWorld() { return WorldManager.world; }
}
    
